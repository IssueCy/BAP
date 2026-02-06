package de.kolja.bap.controller;

import de.kolja.bap.service.AuthService;
import de.kolja.bap.service.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private final AuthService authService = new AuthService();

    @FXML
    private void handleLogin() throws IOException {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (authService.login(user, pass)) {
            SceneManager.switchScene("mainLayout.fxml");
        } else {
            System.out.println("Login fehlgeschlagen");
        }
    }
}