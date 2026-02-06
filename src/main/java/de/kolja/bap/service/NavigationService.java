package de.kolja.bap.service;

import de.kolja.bap.controller.MainController;

public class NavigationService {

    private static MainController mainController;

    public static void register(MainController controller) {
        mainController = controller;
    }

    public static void go(String centerFxml) {
        if (mainController == null) {
            throw new IllegalStateException("MainController not registered");
        }
        mainController.setCenter(centerFxml);
    }
}