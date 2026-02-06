package de.kolja.bap;

import de.kolja.bap.service.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneManager.setStage(stage);
        stage.setTitle("BAP Pro");

        SceneManager.switchScene("login.fxml");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
