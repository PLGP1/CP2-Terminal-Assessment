package model;

public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String birthday;
    private String address;
    private String phone;
    private String sss;
    private String philhealth;
    private String pagibig;
    private String tin;
    private String department;
    private String position;
    private String immediateSupervisor;
    private double salary;

    public Employee(String id, String firstName, String lastName, String birthday, String address,
                String phone, String sss, String philhealth, String pagibig, String tin,
                String department, String position, String supervisor, double salary) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthday = birthday;
    this.address = address;
    this.phone = phone;
    this.sss = sss;
    this.philhealth = philhealth;
    this.pagibig = pagibig;
    this.tin = tin;
    this.department = department;
    this.position = position;
    this.immediateSupervisor = supervisor;
    this.salary = salary;
}

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
    
    public String getSss() {
        return sss;
    }
    
    public String getPhilhealth() {
        return philhealth;
    }
    
    public String getPagibig() {
        return pagibig;
    }
    
    public String getTin() {
        return tin;
    }
    
    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }
    
    public String getImmdediateSupervisor() {
        return immediateSupervisor;
    }

    public double getSalary() {
        return salary;
    }
}
