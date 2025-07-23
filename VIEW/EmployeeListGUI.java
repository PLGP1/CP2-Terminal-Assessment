package view;

import controller.EmployeeController;
import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmployeeListGUI extends JFrame {
    private JPanel employeePanel;
    private JScrollPane scrollPane;
    private JButton backButton;

    public EmployeeListGUI() {
        setTitle("MotorPH - Employee Records");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(employeePanel);

        backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            new DashboardGUI().setVisible(true);
            dispose();
        });

        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        loadEmployeeData();
    }

    private void loadEmployeeData() {
        EmployeeController controller = new EmployeeController();
        List<Employee> employees = controller.loadEmployees();

        for (Employee emp : employees) {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.setBorder(BorderFactory.createTitledBorder("Employee ID: " + emp.getId()));
            panel.add(new JLabel("First Name: " + emp.getFirstName()));
            panel.add(new JLabel("Last Name: " + emp.getLastName()));
            panel.add(new JLabel("Birthday: " + emp.getBirthday()));
            panel.add(new JLabel("Phone: " + emp.getPhone()));
            panel.add(new JLabel("Position: " + emp.getPosition()));
            panel.add(new JLabel("Department: " + emp.getDepartment()));
            panel.add(new JLabel("Salary: ₱" + emp.getSalary()));
            panel.setBackground(Color.WHITE);
            employeePanel.add(panel);
        }

        employeePanel.revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeListGUI().setVisible(true));
    }
}
