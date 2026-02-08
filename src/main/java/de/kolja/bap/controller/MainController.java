package de.kolja.bap.controller;

import de.kolja.bap.service.NavigationService;
import de.kolja.bap.service.SceneManager;
import de.kolja.bap.service.Session;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainController {


    @FXML private BorderPane contentPane;
    @FXML private Label userLabel;
    @FXML private Label timeLabel;
    @FXML private Label dateLabel;

    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE, dd.MM.yyyy");

    @FXML
    public void initialize() {
        NavigationService.register(this);
        setCenter("center_home.fxml");

        userLabel.setText(Session.getInstance().getUsername());
        startClock();
        setDate();
    }

    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> timeLabel.setText(LocalTime.now().format(timeFormat))), new KeyFrame(Duration.seconds(1)));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    private void setDate() {
        dateLabel.setText(LocalDate.now().format(dateFormat));
    }

    // Navigation logic
    @FXML
    private void goHome() {
        NavigationService.go("center_home.fxml");
    }

    @FXML
    private void goAdmin() {
        NavigationService.go("center_admin.fxml");
    }

    @FXML
    private void logout() {
        Stage currentStage = (Stage) contentPane.getScene().getWindow();
        currentStage.close();

        SceneManager.openNewStage("login.fxml", "BAP Login");
    }

    // ----------

    public void setCenter(String fxml) {
        try {
            var resource = getClass().getResource("/de/kolja/bap/view/" + fxml);

            if (resource == null) {
                throw new IllegalArgumentException("FXML nicht gefunden: " + fxml);
            }

            contentPane.setCenter(FXMLLoader.load(resource));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

