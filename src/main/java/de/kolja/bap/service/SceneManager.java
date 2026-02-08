package de.kolja.bap.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    private static Stage primaryStage;

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchScene(String fxml) {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage not set!");
        }
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/de/kolja/bap/view/" + fxml));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stage openNewStage(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/de/kolja/bap/view/" + fxml));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.setMaximized(true);
            stage.show();
            return stage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}