package de.kolja.bap.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SignatureOverlayController {

    @FXML
    private Label urlLabel;

    public void setUrl(String url) {
        urlLabel.setText(url);
    }
}
