package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardGUI extends JFrame {
    private JButton employeeBtn, attendanceBtn, payrollBtn, profileBtn, logoutBtn;

    public DashboardGUI() {
        setTitle("MotorPH Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        employeeBtn = new JButton("Employee Records");
        attendanceBtn = new JButton("Attendance");
        payrollBtn = new JButton("Payroll");
        profileBtn = new JButton("Profile");
        logoutBtn = new JButton("Logout");

        panel.add(employeeBtn);
        panel.add(attendanceBtn);
        panel.add(payrollBtn);
        panel.add(profileBtn);
        panel.add(logoutBtn);

        add(panel);

        // Button listeners
        employeeBtn.addActionListener(e -> {
            new EmployeeListGUI().setVisible(true);
            dispose();
        });

        attendanceBtn.addActionListener(e -> {
            new AttendanceGUI().setVisible(true);
            dispose();
        });

        payrollBtn.addActionListener(e -> {
            new PayrollGUI().setVisible(true);
            dispose();
        });

        profileBtn.addActionListener(e -> {
            new ProfileGUI().setVisible(true);
            dispose();
        });

        logoutBtn.addActionListener(e -> {
            new LoginGUI().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardGUI().setVisible(true));
    }
}
