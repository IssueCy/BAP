package de.kolja.bap.controller;

import de.kolja.bap.service.NavigationService;
import javafx.fxml.FXML;

public class CenterLegitimationController {

    @FXML
    private void centerConfirmCustomerLegitimation() {
        NavigationService.go("center_home.fxml");
        System.out.println("Succesfully created a new customer!");
        // legetimation logic here later
    }
}
