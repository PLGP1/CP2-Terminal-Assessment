package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Attendance {
    private String employeeID;
    private String date;
    private String timeIn;   // format HH:mm
    private String timeOut;  // format HH:mm

    // Getters and setters
    public String getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeIn() {
        return timeIn;
    }
    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * Calculates the total hours worked based on timeIn and timeOut.
     * Returns 0.0 if parsing fails or if timeOut is before timeIn.
     */
    public double getTotalHours() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime in = LocalTime.parse(timeIn, formatter);
            LocalTime out = LocalTime.parse(timeOut, formatter);

            if (out.isBefore(in)) {
                return 0.0; // invalid time range
            }

            Duration duration = Duration.between(in, out);
            return duration.toMinutes() / 60.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
}
