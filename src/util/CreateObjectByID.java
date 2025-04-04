package util;

import model.*;

import java.io.File;
import java.util.List;

public class CreateObjectByID {
    private static final File employeeFile =  new File("E:\\CS M2\\src\\repository\\employees.csv");
    private static final File customerFile =  new File("E:\\CS M2\\src\\repository\\customers.csv");

    public static Person getPersonByID(String id) {
        List<String> employeeData = ReadAndWriteData.readFile(employeeFile);
        for (String line : employeeData) {
            String[] partsData = line.split(",");
            if (partsData.length < 8 || !partsData[0].equals(id)) {
                continue;
            }

            try {
                String name = partsData[1];
                String phoneNumber = partsData[2];
                String emailAddress = partsData[3];
                int indexProject = Integer.parseInt(partsData[4]);
                String typeOfEmployee = partsData[5];
                int salary = Integer.parseInt(partsData[6]);
                int yearOfJoining = Integer.parseInt(partsData[7]);

                return switch (typeOfEmployee.toLowerCase()) {
                    case "employee" ->
                            new Employee(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee, salary, id);
                    case "leader" -> {
                        int indexGroup = Integer.parseInt(partsData[8]);
                        yield new Leader(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee, salary, id, indexGroup);
                    }
                    case "manager" -> {
                        int experienceYear = Integer.parseInt(partsData[8]);
                        yield new Manager(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee, salary, id, experienceYear);
                    }
                    default -> {
                        System.err.println("Invalid type of employee: " + typeOfEmployee);
                        yield null;
                    }
                };
            } catch (Exception e) {
                System.err.println("Error parsing employee data for ID: " + id);
                return null;
            }
        }

        // Đọc danh sách khách hàng
        List<String> customerData = ReadAndWriteData.readFile(customerFile);
        for (String line : customerData) {
            String[] partsData = line.split(",");
            if (partsData.length < 6 || !partsData[0].equals(id)) {
                continue;
            }

            try {
                String name = partsData[1];
                String phoneNumber = partsData[2];
                String emailAddress = partsData[3];
                int indexProject = Integer.parseInt(partsData[4]);
                int level = Integer.parseInt(partsData[5]);

                return new Customer(name, phoneNumber, emailAddress, indexProject, level, id);
            } catch (Exception e) {
                System.err.println("Error parsing customer data for ID: " + id);
                return null;
            }
        }

        System.err.println("No person found with ID: " + id);
        return null;
    }

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
                        parts[5],  // typeOfEmployee
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

    public static Manager getManagerByID(String id) {
        File employeesFile = new File("E:\\CS M2\\src\\repository\\employees.csv");
        List<String> employeeData = ReadAndWriteData.readFile(employeesFile);

        for (String line : employeeData) {
            String[] parts = line.split(",");

            if (parts.length < 9) {
                System.err.println("Invalid line format: " + line);
                continue;
            }

            // Kiểm tra đúng ID và đúng kiểu manager
            if (parts[0].equals(id) && parts[5].equalsIgnoreCase("manager")) {
                try {
                    String name = parts[1];
                    String phoneNumber = parts[2];
                    String emailAddress = parts[3];
                    int indexProject = Integer.parseInt(parts[4]);
                    String typeOfEmployee = parts[5];
                    int salary = Integer.parseInt(parts[6]);
                    int yearOfJoining = Integer.parseInt(parts[7]);
                    int experienceYear = Integer.parseInt(parts[8]);

                    return new Manager(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee, salary, id, experienceYear);
                } catch (Exception e) {
                    System.err.println("Error parsing manager data for ID: " + id);
                    return null;
                }
            }
        }

        System.err.println("Manager with ID " + id + " not found.");
        return null;
    }

}
