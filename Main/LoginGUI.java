package view;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginGUI() {
        setTitle("MotorPH Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(new JLabel());  // Empty space
        add(loginButton);

        loginButton.addActionListener(e -> performLogin());

        setVisible(true);
    }

    private void performLogin() {
        String enteredUsername = usernameField.getText().trim();
        String enteredPassword = new String(passwordField.getPassword()).trim();
        boolean loginSuccessful = false;
        String role = "";

        try {
            File file = new File("account.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(this, "account.txt file not found!", "File Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // skip header
                }

                String[] parts = line.split(":");
                if (parts.length >= 4) {
                    String username = parts[1].trim();
                    String password = parts[2].trim();
                    role = parts[3].trim();

                    if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                        loginSuccessful = true;
                        break;
                    }
                }
            }

            reader.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading account.txt: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (loginSuccessful) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            this.dispose();
            new DashboardGUI(); // Open your dashboard
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }
}

