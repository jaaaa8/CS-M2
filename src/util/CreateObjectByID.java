package util;

import model.*;

import java.io.File;
import java.util.List;

public class CreateObjectByID {
    private static final File employeeFile =  new File("E:\\CS M2\\src\\repository\\employees.csv");
    private static final File customerFile =  new File("E:\\CS M2\\src\\repository\\customers.csv");
    private static final File orderFile =  new File("E:\\CS M2\\src\\repository\\orders.csv");

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
        List<String> employeeData = ReadAndWriteData.readFile(employeeFile);

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

    public static Orders getOrdersByID(int id) {
        List<String> ordersData = ReadAndWriteData.readFile(orderFile);

        for (String line : ordersData) {
            String[] parts = line.split(",");
            if (parts.length < 5) {
                System.err.println("Invalid order data: " + line);
                continue;
            }

            try {
                int orderId = Integer.parseInt(parts[0]);
                if (orderId == id) {
                    String customerId = parts[1];
                    String description = parts[2];
                    String typeOfOrder = parts[3];
                    int budget = Integer.parseInt(parts[4]);

                    Customer customer = getCustomerByID(customerId);
                    if (customer == null) {
                        System.err.println("Customer not found for ID: " + customerId);
                        return null;
                    }

                    return new Orders(orderId, customer, description, typeOfOrder, budget);
                }
            } catch (Exception e) {
                System.err.println("Error parsing order data: " + line);
            }
        }

        return null;
    }

}
