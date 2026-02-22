package de.kolja.bap.service;

import com.sun.net.httpserver.HttpServer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.Desktop;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class SignatureServer {

    private static HttpServer server;
    private static boolean running = false;

    private static final File SIGNATURE_DIR = new File("signatures");

    public static void start() {
        if (running) return;

        try {
            if (!SIGNATURE_DIR.exists()) SIGNATURE_DIR.mkdirs();

            server = HttpServer.create(new InetSocketAddress(8080), 0);

            server.createContext("/", exchange -> {
                String response = "Signature Server running";
                byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
                exchange.sendResponseHeaders(200, bytes.length);
                exchange.getResponseBody().write(bytes);
                exchange.close();
            });

            // Canvas-Seite
            server.createContext("/sign", exchange -> {
                String html = SignatureHtml.PAGE;
                byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(200, bytes.length);
                exchange.getResponseBody().write(bytes);
                exchange.close();
            });

            // Upload
            server.createContext("/upload", exchange -> {
                String data = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                String base64 = data.split(",")[1];
                byte[] imageBytes = Base64.getDecoder().decode(base64);

                Platform.runLater(() -> showPreview(imageBytes));
                createPdf(imageBytes);

                exchange.sendResponseHeaders(200, 0);
                exchange.close();
            });

            server.start();
            running = true;
            System.out.println("SignatureServer started");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showPreview(byte[] imageBytes) {
        Image img = new Image(new ByteArrayInputStream(imageBytes));
        ImageView iv = new ImageView(img);
        iv.setPreserveRatio(true);
        iv.setFitWidth(400);

        Stage stage = new Stage();
        stage.setTitle("Unterschrift prüfen");
        stage.setScene(new Scene(new StackPane(iv), 420, 260));
        stage.show();
    }

    private static void createPdf(byte[] imageBytes) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDImageXObject img = PDImageXObject.createFromByteArray(
                    doc,
                    imageBytes,
                    "signature"
            );

            float targetWidth = 350;
            float scale = targetWidth / img.getWidth();
            float targetHeight = img.getHeight() * scale;

            try (PDPageContentStream cs = new PDPageContentStream(doc, page)) {
                cs.drawImage(img, 100, 400, targetWidth, targetHeight);
            }

            String ts = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            File pdf = new File(SIGNATURE_DIR, "signature_" + ts + ".pdf");
            doc.save(pdf);

            Desktop.getDesktop().open(pdf);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (server != null) {
            server.stop(0);
            running = false;
            System.out.println("SignatureServer stopped");
        }
    }
}
