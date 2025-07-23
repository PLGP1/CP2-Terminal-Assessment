package view;

import controller.ProfileController;
import model.Profile;

import javax.swing.*;
import java.awt.*;

public class ProfileGUI extends JFrame {
    private JLabel lblTitle, lblEmpID, lblName, lblPosition, lblDepartment;
    private JTextField txtEmpID, txtName, txtPosition, txtDepartment;
    private JButton btnLoad;

    public ProfileGUI() {
        setTitle("MotorPH - Employee Profile");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        lblTitle = new JLabel("Employee Profile", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitle);
        add(new JLabel("")); // Empty

        lblEmpID = new JLabel("Employee ID:");
        txtEmpID = new JTextField();
        add(lblEmpID);
        add(txtEmpID);

        lblName = new JLabel("Name:");
        txtName = new JTextField();
        txtName.setEditable(false);
        add(lblName);
        add(txtName);

        lblPosition = new JLabel("Position:");
        txtPosition = new JTextField();
        txtPosition.setEditable(false);
        add(lblPosition);
        add(txtPosition);

        lblDepartment = new JLabel("Department:");
        txtDepartment = new JTextField();
        txtDepartment.setEditable(false);
        add(lblDepartment);
        add(txtDepartment);

        btnLoad = new JButton("Load Profile");
        btnLoad.addActionListener(e -> loadProfile());
        add(btnLoad);
        add(new JLabel("")); // empty

        setVisible(true);
    }

    private void loadProfile() {
        String empID = txtEmpID.getText();
        Profile profile = ProfileController.getProfileById(empID);

        if (profile != null) {
            txtName.setText(profile.getName());
            txtPosition.setText(profile.getPosition());
            txtDepartment.setText(profile.getDepartment());
        } else {
            JOptionPane.showMessageDialog(this, "Employee profile not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProfileGUI::new);
    }
}
