package view;

import java.awt.*;
import javax.swing.*;
import view.ProfileGUI;
import view.AttendanceGUI;
import view.PayrollGUI;
import view.LoginGUI;

public class DashboardGUI extends JFrame {

    public DashboardGUI() {
        setTitle("MotorPH Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to MotorPH Employee App", SwingConstants.CENTER);
        add(welcomeLabel);

        JButton profileBtn = new JButton("Profile");
        JButton attendanceBtn = new JButton("Attendance");
        JButton payrollBtn = new JButton("Payroll");
        JButton logoutBtn = new JButton("Logout");

        add(profileBtn);
        add(attendanceBtn);
        add(payrollBtn);
        add(logoutBtn);

        profileBtn.addActionListener(e -> {
            dispose();
            new ProfileGUI(); // Make sure ProfileGUI is also updated to use .txt
        });

        attendanceBtn.addActionListener(e -> {
            dispose();
            new AttendanceGUI(); // Make sure AttendanceGUI uses .txt file format
        });

        payrollBtn.addActionListener(e -> {
            dispose();
            new PayrollGUI(); // Same here: PayrollGUI must read employee.txt & attendance.txt using '..'
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginGUI();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DashboardGUI::new);
    }
}
