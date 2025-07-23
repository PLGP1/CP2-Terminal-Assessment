package model;

public class Profile {
    private String employeeID;
    private String name;
    private String position;
    private String department;

    public Profile(String employeeID, String name, String position, String department) {
        this.employeeID = employeeID;
        this.name = name;
        this.position = position;
        this.department = department;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }
}
