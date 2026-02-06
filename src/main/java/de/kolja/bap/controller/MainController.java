package de.kolja.bap.controller;

import de.kolja.bap.service.NavigationService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane root;

    @FXML
    public void initialize() {
        NavigationService.register(this);
        setCenter("center_home.fxml");
    }

    public void setCenter(String fxml) {
        try {
            var resource = getClass().getResource("/de/kolja/bap/view/" + fxml);

            if (resource == null) {
                throw new IllegalArgumentException("FXML nicht gefunden: " + fxml);
            }

            root.setCenter(FXMLLoader.load(resource));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

