package service.impl;

import model.Customer;
import service.ICustomerService;
import util.ReadAndWriteData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class CustomerService extends ShowProject implements ICustomerService {
    private static final File customers = new File("E:\\CS M2\\src\\repository\\customers.csv");
    private static final boolean APPEND = true;
    private static final boolean NOT_APPEND = false;

    @Override
    public List<Customer> customerList() {
        List<Customer> customerData = new ArrayList<>();
        List<String> linesData = ReadAndWriteData.readFile(customers);

        for (String line : linesData) {
            String[] partsData = line.split(",");
            if (partsData.length < 6) {
                System.err.println("Data format exception at line: " + line);
                continue;
            }

            try {
                String id = partsData[0];
                String name = partsData[1];
                String phoneNumber = partsData[2];
                String emailAddress = partsData[3];
                int indexProject = Integer.parseInt(partsData[4]);
                int level = Integer.parseInt(partsData[5]);

                customerData.add(new Customer(name, phoneNumber, emailAddress, indexProject, level, id));
            } catch (NumberFormatException e) {
                System.err.println("Error parsing numeric values in line: " + line);
            }
        }
        return customerData;
    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public boolean updateCustomer(String id) {
        return false;
    }

    @Override
    public void deleteCustomer(Customer customer) {

    }

    @Override
    public void pay(Customer customer) {
        int index = customer.getIndexProject();
        File projectFolder = new File("projects");

        if (!projectFolder.exists() || !projectFolder.isDirectory()) {
            System.err.println("Project directory not found!");
            return;
        }

        File[] projectFiles = projectFolder.listFiles((dir, name) ->
                name.startsWith(String.format("%03d", index))
        );

        if (projectFiles == null || projectFiles.length == 0) {
            System.err.println("No matching project file found for index: " + index);
            return;
        }

        File projectFile = projectFiles[0];

        // Đọc dữ liệu từ file
        List<String> projectData = ReadAndWriteData.readFile(projectFile);

        boolean updated = false;

        for (int i = 0; i < projectData.size(); i++) {
            if (projectData.get(i).contains("Pay: Not yet.")) {
                projectData.set(i, "Pay: Already.");
                updated = true;
                break;
            }
        }

        // Nếu có thay đổi thì ghi lại dữ liệu vào file
        if (updated) {
            ReadAndWriteData.writeToFile(projectFile, projectData, NOT_APPEND);
            System.out.println("Payment status updated successfully!");
        } else {
            System.out.println("Payment was already marked as 'Already.'. No changes made.");
        }
    }

}
