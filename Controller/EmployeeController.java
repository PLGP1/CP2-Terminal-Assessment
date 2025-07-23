package controller;

import model.Employee;
import java.io.*;
import java.util.*;
import java.nio.file.*;

public class EmployeeController {
    private static final String FILE_PATH = "src/resources/employee.csv";

    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                // Handle CSV lines with quoted fields containing commas
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (data.length < 14) continue; // Skip invalid lines

                // Extract fields
                String id = data[0].trim().replace("\"", "");
                String lastName = data[1].trim().replace("\"", "");
                String firstName = data[2].trim().replace("\"", "");
                String birthday = data[3].trim().replace("\"", "");
                String address = data[4].trim().replace("\"", "");
                String phone = data[5].trim().replace("\"", "");
                String sss = data[6].trim().replace("\"", "");
                String philhealth = data[7].trim().replace("\"", "");
                String pagibig = data[8].trim().replace("\"", "");
                String tin = data[9].trim().replace("\"", "");
                String department = data[10].trim().replace("\"", "");
                String position = data[11].trim().replace("\"", "");
                String immediateSupervisor = data[12].trim().replace("\"", "");
                String salaryStr = data[13].trim().replace("\"", "").replace(",", "");
                double salary = Double.parseDouble(salaryStr);

                // Create Employee object
                Employee emp = new Employee(
                    id, firstName, lastName, birthday, address, phone,
                    sss, philhealth, pagibig, tin, department,
                    position, immediateSupervisor, salary
                );

                employees.add(emp);
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
