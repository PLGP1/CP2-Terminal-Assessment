package controller;

import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import model.Payroll;

public class PayrollController {
    private static final String EMPLOYEE_CSV = "src/resources/employee.csv";
    private static final String ATTENDANCE_CSV = "src/resources/attendance.csv";

    public static List<Payroll> calculatePayrolls() {
        Map<String, Double> salaryMap = loadSalaries();
        Map<String, String> nameMap = loadNames();
        Map<String, Double> hoursMap = calculateWorkHours();

        List<Payroll> payrolls = new ArrayList<>();
        for (String empID : hoursMap.keySet()) {
            double hoursWorked = hoursMap.get(empID);
            double hourlyRate = salaryMap.getOrDefault(empID, 0.0);
            String name = nameMap.getOrDefault(empID, "Unknown");

            Payroll payroll = new Payroll(empID, name, hoursWorked, hourlyRate);
            payrolls.add(payroll);
        }
        return payrolls;
    }

    private static Map<String, Double> loadSalaries() {
        Map<String, Double> salaryMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_CSV))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { isFirstLine = false; continue; } // Skip header
                String[] data = line.split(",");
                if (data.length >= 12) {
                    String empID = data[0];
                    double salary = Double.parseDouble(data[11]);
                    salaryMap.put(empID, salary / 160); // Convert to hourly (160 hrs/month)
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return salaryMap;
    }

    private static Map<String, String> loadNames() {
        Map<String, String> nameMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYEE_CSV))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { isFirstLine = false; continue; } // Skip header
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String empID = data[0];
                    String name = data[1] + " " + data[2];
                    nameMap.put(empID, name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameMap;
    }

    private static Map<String, Double> calculateWorkHours() {
        Map<String, Double> hoursMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ATTENDANCE_CSV))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { isFirstLine = false; continue; } // Skip header
                String[] data = line.split(",");
                if (data.length >= 4) {
                    String empID = data[0];
                    LocalTime timeIn = LocalTime.parse(data[2]);
                    LocalTime timeOut = LocalTime.parse(data[3]);
                    Duration duration = Duration.between(timeIn, timeOut);
                    double hours = duration.toMinutes() / 60.0;

                    hoursMap.put(empID, hoursMap.getOrDefault(empID, 0.0) + hours);
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return hoursMap;
    }
}
