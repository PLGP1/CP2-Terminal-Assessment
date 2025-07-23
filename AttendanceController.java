package controller;

import model.Attendance;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceController {
    private static final String ATTENDANCE_FILE = "src/resources/attendance.csv";

    public static List<Attendance> readAttendanceFromCSV() {
        List<Attendance> attendanceList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ATTENDANCE_FILE))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip header
                    continue;
                }
                String[] data = line.split(",", -1);
                if (data.length >= 4) {
                    Attendance att = new Attendance();
                    att.setEmployeeID(data[0].trim());
                    att.setDate(data[1].trim());
                    att.setTimeIn(data[2].trim());
                    att.setTimeOut(data[3].trim());
                    attendanceList.add(att);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    public static void saveAttendanceToCSV(List<Attendance> attendanceList) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ATTENDANCE_FILE))) {
            // Write CSV header
            pw.println("Employee ID,Date,Time In,Time Out");

            for (Attendance att : attendanceList) {
                StringBuilder sb = new StringBuilder();
                sb.append(att.getEmployeeID()).append(",");
                sb.append(att.getDate()).append(",");
                sb.append(att.getTimeIn()).append(",");
                sb.append(att.getTimeOut());

                pw.println(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Calculates total hours worked per employee from attendance records
    public static Map<String, Double> calculateHoursWorked() {
        List<Attendance> records = readAttendanceFromCSV();
        Map<String, Double> totalHoursMap = new HashMap<>();

        for (Attendance att : records) {
            double hours = att.getTotalHours(); // Implement getTotalHours() in Attendance model
            totalHoursMap.put(att.getEmployeeID(),
                    totalHoursMap.getOrDefault(att.getEmployeeID(), 0.0) + hours);
        }
        return totalHoursMap;
    }
}
