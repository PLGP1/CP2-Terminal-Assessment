package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

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
        add(new JLabel());  // Empty label for spacing
        add(loginButton);

        loginButton.addActionListener(e -> performLogin());

        setVisible(true);
    }
    
   private void performLogin() {
    String enteredUsername = usernameField.getText();
    String enteredPassword = new String(passwordField.getPassword());

    boolean loginSuccessful = false;

    try {
        File file = new File("account.csv");
System.out.println("Absolute path: " + file.getAbsolutePath());
System.out.println("File exists? " + file.exists());

        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "File not found: " + file.getAbsolutePath(), "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ADMIN\\NetbeansProjects\\MotorPH App\\account.csv"));
        String line;
        boolean isFirstLine = true;

        while ((line = reader.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue; // skip header
            }

            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String username = parts[0].trim();
                String password = parts[1].trim();

                if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                    loginSuccessful = true;
                    break;
                }
            }
        }

        reader.close();
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Error reading account.csv file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
    }

    if (loginSuccessful) {
        JOptionPane.showMessageDialog(this, "Login successful!");
        this.dispose();
        new DashboardGUI(); // Replace with your actual dashboard
    } else {
        JOptionPane.showMessageDialog(this, "Invalid username or password.");
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }
}
