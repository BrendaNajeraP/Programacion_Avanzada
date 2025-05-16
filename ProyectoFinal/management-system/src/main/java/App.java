package com.management;

import controller.MainController;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting the application...");
        // Use SwingUtilities to ensure proper EDT initialization
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Initializing main controller...");
                MainController mainController = new MainController();
                System.out.println("Showing main view...");
                mainController.showMainView();
            } catch (Exception e) {
                System.err.println("Error starting application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}