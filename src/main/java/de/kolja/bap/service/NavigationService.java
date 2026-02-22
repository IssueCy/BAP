package de.kolja.bap.service;

import de.kolja.bap.controller.MainController;

import java.util.Stack;

public class NavigationService {

    private static MainController mainController;
    private static final Stack<String> history = new Stack<>();

    public static void register(MainController controller) {
        mainController = controller;
        history.clear();
    }

    public static void go(String centerFxml) {
        if (mainController == null) {
            throw new IllegalStateException("MainController not registered");
        }

        history.push(centerFxml);
        mainController.setCenter(centerFxml);
    }

    public static void back() {
        if (history.size() <= 1) {
            return;
        }

        history.pop();
        String previous = history.peek();
        mainController.setCenter(previous);
    }
}