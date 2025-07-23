package view;

import controller.PayrollController;
import model.Payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PayrollGUI extends JFrame {
    private JTable payrollTable;
    private DefaultTableModel tableModel;

    public PayrollGUI() {
        setTitle("Payroll");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columns = {"Employee ID", "Name", "Hours Worked", "Hourly Rate", "Gross Pay", "Deductions", "Net Pay"};
        tableModel = new DefaultTableModel(columns, 0);
        payrollTable = new JTable(tableModel);
        loadPayrollData();

        JScrollPane scrollPane = new JScrollPane(payrollTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadPayrollData() {
        List<Payroll> payrollList = PayrollController.calculatePayrolls();
        for (Payroll p : payrollList) {
            Object[] row = {
                p.getEmployeeID(),
                p.getName(),
                String.format("%.2f", p.getHoursWorked()),
                String.format("₱%.2f", p.getHourlyRate()),
                String.format("₱%.2f", p.getGrossPay()),
                String.format("₱%.2f", p.getDeductions()),
                String.format("₱%.2f", p.getNetPay())
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PayrollGUI().setVisible(true));
    }
}
