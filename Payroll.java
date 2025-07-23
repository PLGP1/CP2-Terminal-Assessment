package model;

public class Payroll {
    private String employeeID;
    private String name;
    private double hoursWorked;
    private double hourlyRate;
    private double grossPay;
    private double deductions;
    private double netPay;

    public Payroll(String employeeID, String name, double hoursWorked, double hourlyRate) {
        this.employeeID = employeeID;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.grossPay = hoursWorked * hourlyRate;
        this.deductions = grossPay * 0.10; // Example 10% deduction
        this.netPay = grossPay - deductions;
    }

    // Getters
    public String getEmployeeID() { return employeeID; }
    public String getName() { return name; }
    public double getHoursWorked() { return hoursWorked; }
    public double getHourlyRate() { return hourlyRate; }
    public double getGrossPay() { return grossPay; }
    public double getDeductions() { return deductions; }
    public double getNetPay() { return netPay; }
}
