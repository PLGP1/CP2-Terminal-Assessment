package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AttendanceGUI extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private final String[] columns = {
        "Employee ID", "Date", "Time In", "Time Out"
    };

    private final String attendanceFile = "src/resources/attendance.csv";

    public AttendanceGUI() {
        setTitle("Attendance Records");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        addBtn.addActionListener(e -> addRecord());
        updateBtn.addActionListener(e -> updateRecord());
        deleteBtn.addActionListener(e -> deleteRecord());

        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(updateBtn);
        btnPanel.add(deleteBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        loadAttendance();
        setVisible(true);
    }

    private void loadAttendance() {
        model.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader(attendanceFile))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // skip header
                    continue;
                }

                String[] data = line.split(",", -1);
                if (data.length == columns.length) {
                    model.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading attendance: " + e.getMessage());
        }
    }

    private void addRecord() {
        String empId = JOptionPane.showInputDialog(this, "Employee ID:");
        if (empId == null || empId.trim().isEmpty()) return;

        String date = JOptionPane.showInputDialog(this, "Date (YYYY-MM-DD):");
        if (date == null || date.trim().isEmpty()) return;

        String timeIn = JOptionPane.showInputDialog(this, "Time In (HH:mm):");
        if (timeIn == null || timeIn.trim().isEmpty()) return;

        String timeOut = JOptionPane.showInputDialog(this, "Time Out (HH:mm):");
        if (timeOut == null || timeOut.trim().isEmpty()) return;

        model.addRow(new String[] { empId.trim(), date.trim(), timeIn.trim(), timeOut.trim() });
        saveAttendance();
    }

    private void updateRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) return;

        for (int i = 0; i < columns.length; i++) {
            String updatedValue = JOptionPane.showInputDialog(this, "Update " + columns[i] + ":", model.getValueAt(selectedRow, i));
            if (updatedValue != null) {
                model.setValueAt(updatedValue.trim(), selectedRow, i);
            }
        }
        saveAttendance();
    }

    private void deleteRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
            saveAttendance();
        }
    }

    private void saveAttendance() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(attendanceFile))) {
            // Write header first
            pw.println(String.join(",", columns));
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    sb.append(model.getValueAt(i, j));
                    if (j < model.getColumnCount() - 1) sb.append(",");
                }
                pw.println(sb);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving attendance: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AttendanceGUI::new);
    }
}
