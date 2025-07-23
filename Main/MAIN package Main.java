package main;

import view.LoginGUI;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginGUI().setVisible(true);
        });
    }
}
