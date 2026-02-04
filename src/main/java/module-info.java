module de.kolja.bap {
    requires javafx.controls;
    requires javafx.fxml;


    opens de.kolja.bap to javafx.fxml;
    exports de.kolja.bap;
}