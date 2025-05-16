package view;

import model.User;

public class MainView {
    public void displayUserInfo(User user) {
        System.out.println("User ID: " + user.getId());
        System.out.println("User Name: " + user.getName());
        System.out.println("User Email: " + user.getEmail());
    }

    public void showError(String message) {
        System.err.println("Error: " + message);
    }
}