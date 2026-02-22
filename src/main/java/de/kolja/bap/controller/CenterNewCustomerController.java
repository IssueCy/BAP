package de.kolja.bap.controller;

import de.kolja.bap.service.NavigationService;
import de.kolja.bap.service.SignatureServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CenterNewCustomerController {

    @FXML
    private Button btnSignature;

    @FXML
    private TextField tfKundenName;

    @FXML
    private void handleSignature() {
        SignatureServer.start();

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/de/kolja/bap/view/signature_overlay.fxml")
            );
            Stage stage = new Stage();
            stage.setTitle("Unterschrift erfassen");
            stage.setScene(new Scene(loader.load(), 450, 150));

            SignatureOverlayController c = loader.getController();
            c.setUrl("http://<PC-IP>:8080/sign");

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void centerConfirmCustomerInput() {
        NavigationService.go("center_legetimation.fxml");
    }
}
