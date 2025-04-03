package util;

import model.Customer;
import model.Employee;
import model.Leader;

import java.io.File;
import java.util.List;

public class CreateObjectByID {
    private static final File employeeFile =  new File("E:\\CS M2\\src\\repository\\employees.csv");
    private static final File customerFile =  new File("E:\\CS M2\\src\\repository\\customers.csv");

    public static Employee getEmployeeByID(String id) {
        List<String> employeeData = ReadAndWriteData.readFile(employeeFile);

        for (String line : employeeData) {
            String[] parts = line.split(",");
            if (parts.length < 8) {
                System.err.println("Invalid data format: " + line);
                continue;
            }

            if (!parts[0].equals(id)) {
                continue;
            }

            try {
                return new Employee(
                        parts[1],  // name
                        parts[2],  // phoneNumber
                        parts[3],  // emailAddress
                        Integer.parseInt(parts[4]), // indexProject
                        Integer.parseInt(parts[7]), // yearOfJoining
                        parts[5],  // typeOfEmployee
                        Integer.parseInt(parts[6]), // salary
                        parts[0]   // id
                );
            } catch (NumberFormatException e) {
                System.err.println("Error parsing numeric values: " + line);
                return null;
            }
        }
        System.err.println("Employee with ID " + id + " not found.");
        return null;
    }

    public static Leader getLeaderByID(String id) {
        List<String> employeeData = ReadAndWriteData.readFile(employeeFile);

        for (String line : employeeData) {
            String[] parts = line.split(",");
            if (parts.length < 9) { // Leader có ít nhất 9 trường dữ liệu
                System.err.println("Invalid data format: " + line);
                continue;
            }

            if (!parts[0].equals(id)) {
                continue;
            }

            try {
                return new Leader(
                        parts[1],  // name
                        parts[2],  // phoneNumber
                        parts[3],  // emailAddress
                        Integer.parseInt(parts[4]), // indexProject
                        Integer.parseInt(parts[7]), // yearOfJoining
                        parts[5],  // typeOfEmployee (chắc chắn là "leader")
                        Integer.parseInt(parts[6]), // salary
                        parts[0],   // id
                        Integer.parseInt(parts[8]) // indexGroup
                );
            } catch (NumberFormatException e) {
                System.err.println("Error parsing numeric values: " + line);
                return null;
            }
        }

        System.err.println("Leader with ID " + id + " not found.");
        return null;
    }

    public static Customer getCustomerByID(String id) {
        List<String> customerData = ReadAndWriteData.readFile(customerFile);

        for (String line : customerData) {
            String[] parts = line.split(",");
            if (parts.length < 6) { // Customer có ít nhất 6 trường dữ liệu
                System.err.println("Invalid data format: " + line);
                continue;
            }

            // Kiểm tra ID có khớp không
            if (!parts[0].equals(id)) {
                continue;
            }

            try {
                return new Customer(
                        parts[1],  // name
                        parts[2],  // phoneNumber
                        parts[3],  // emailAddress
                        Integer.parseInt(parts[4]), // indexProject
                        Integer.parseInt(parts[5]), // level
                        parts[0]   // id
                );
            } catch (NumberFormatException e) {
                System.err.println("Error parsing numeric values: " + line);
                return null;
            }
        }

        System.err.println("Customer with ID " + id + " not found.");
        return null;
    }

}
