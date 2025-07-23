package view;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PayrollGUI extends JFrame {
    private JTable payrollTable;
    private DefaultTableModel tableModel;

    public PayrollGUI() {
        setTitle("MotorPH - Payroll");
        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel();
        payrollTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(payrollTable);
        add(scrollPane, BorderLayout.CENTER);

        loadPayrollData();

        setVisible(true);
    }

    private void loadPayrollData() {
        File employeeFile = new File("employee.txt");
        File attendanceFile = new File("attendance.txt");

        if (!employeeFile.exists() || !attendanceFile.exists()) {
            JOptionPane.showMessageDialog(this, "employee.txt or attendance.txt file not found!", "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Load employees into a Map keyed by employee ID
        Map<String, Employee> employees = new HashMap<>();

        try (BufferedReader empReader = new BufferedReader(new FileReader(employeeFile))) {
            String line;
            boolean firstLine = true;

            while ((line = empReader.readLine()) != null) {
                if (firstLine) { // header
                    String[] headers = line.split(":");
                    for (String header : headers) {
                        tableModel.addColumn(header.trim());
                    }
                    // Add payroll columns
                    tableModel.addColumn("Hours Worked");
                    tableModel.addColumn("Gross Pay");
                    tableModel.addColumn("Deductions");
                    tableModel.addColumn("Net Pay");

                    firstLine = false;
                    continue;
                }
                String[] parts = line.split(":");
                if (parts.length >= 6) { 
                    // Assuming columns:
                    // 0: id, 1: name, 2: position, 3: department, 4: ratePerHour, 5: (other...)
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String position = parts[2].trim();
                    String department = parts[3].trim();
                    double ratePerHour = Double.parseDouble(parts[4].trim());

                    employees.put(id, new Employee(id, name, position, department, ratePerHour));
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error reading employee.txt: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calculate hours worked from attendance.txt (sum of daily hours)
        Map<String, Double> hoursWorkedMap = new HashMap<>();
        try (BufferedReader attReader = new BufferedReader(new FileReader(attendanceFile))) {
            String line;
            boolean firstLine = true;

            while ((line = attReader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }
                String[] parts = line.split(":");
                if (parts.length >= 4) {
                    String empId = parts[0].trim();
                    String timeIn = parts[2].trim();
                    String timeOut = parts[3].trim();

                    double hours = calculateHours(timeIn, timeOut);
                    hoursWorkedMap.put(empId, hoursWorkedMap.getOrDefault(empId, 0.0) + hours);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading attendance.txt: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Fill table rows with payroll info
        for (Employee emp : employees.values()) {
            double hoursWorked = hoursWorkedMap.getOrDefault(emp.id, 0.0);
            double grossPay = emp.ratePerHour * hoursWorked;
            double deductions = grossPay * 0.12;  // example: 12% deductions
            double netPay = grossPay - deductions;

            Vector<Object> row = new Vector<>();
            row.add(emp.id);
            row.add(emp.name);
            row.add(emp.position);
            row.add(emp.department);
            row.add(emp.ratePerHour);
            // Add empty columns if employee.txt has more columns beyond these 5
            // OR you can add more fields here accordingly.

            row.add(hoursWorked);
            row.add(String.format("%.2f", grossPay));
            row.add(String.format("%.2f", deductions));
            row.add(String.format("%.2f", netPay));

            tableModel.addRow(row);
        }
    }

    // Helper to calculate worked hours between timeIn and timeOut (format "HH:mm")
    private double calculateHours(String timeIn, String timeOut) {
        try {
            String[] inParts = timeIn.split(":");
            String[] outParts = timeOut.split(":");

            int inHour = Integer.parseInt(inParts[0]);
            int inMinute = Integer.parseInt(inParts[1]);

            int outHour = Integer.parseInt(outParts[0]);
            int outMinute = Integer.parseInt(outParts[1]);

            double inTime = inHour + inMinute / 60.0;
            double outTime = outHour + outMinute / 60.0;

            double hoursWorked = outTime - inTime;
            return hoursWorked > 0 ? hoursWorked : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private static class Employee {
        String id, name, position, department;
        double ratePerHour;

        Employee(String id, String name, String position, String department, double ratePerHour) {
            this.id = id;
            this.name = name;
            this.position = position;
            this.department = department;
            this.ratePerHour = ratePerHour;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PayrollGUI::new);
    }
}
