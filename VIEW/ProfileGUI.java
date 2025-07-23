package view;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class ProfileGUI extends JFrame {
    private JLabel nameLabel, positionLabel, departmentLabel;
    private String employeeId;

    public ProfileGUI(String employeeId) {
        this.employeeId = employeeId;
        setTitle("MotorPH - Profile");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        nameLabel = new JLabel();
        positionLabel = new JLabel();
        departmentLabel = new JLabel();

        add(new JLabel("Name:"));
        add(nameLabel);

        add(new JLabel("Position:"));
        add(positionLabel);

        add(new JLabel("Department:"));
        add(departmentLabel);

        loadProfileData();

        setVisible(true);
    }

    private void loadProfileData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("employee.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 5 && parts[0].equals(employeeId)) {
                    nameLabel.setText(parts[1]);
                    positionLabel.setText(parts[3]);
                    departmentLabel.setText(parts[4]);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading employee.txt: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProfileGUI("10003"));  // test with a sample ID
    }
}
