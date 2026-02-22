package de.kolja.bap;

import de.kolja.bap.service.SceneManager;
import de.kolja.bap.service.SignatureServer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager.setStage(stage);
        stage.setTitle("BAP Pro");

        SceneManager.switchScene("login.fxml");
        stage.show();
        stage.setMaximized(true);
    }

    @Override
    public void stop() {
        SignatureServer.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
