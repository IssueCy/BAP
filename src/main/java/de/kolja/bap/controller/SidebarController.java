package de.kolja.bap.controller;

import de.kolja.bap.service.NavigationService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SidebarController {

    @FXML public Button sidebar_home_button;
    @FXML public Button sidebar_admin_button;

    @FXML
    private void goHome() {
        NavigationService.go("center_home.fxml");
    }

    @FXML
    private void goAdmin() {
        NavigationService.go("center_admin.fxml");
    }

}
