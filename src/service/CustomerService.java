package service;

import model.Customer;
import service.impl.ICustomerService;
import service.impl.IShowProject;
import dto.CreateObjectByID;
import util.ReadAndWriteData;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class CustomerService implements ICustomerService, IShowProject {
    private static final String customersFilePath = "E:\\CS M2\\src\\repository\\customers.csv";
    private static final boolean APPEND = true;
    private static final boolean NOT_APPEND = false;

    @Override
    public List<Customer> customerList() {
        List<Customer> customerData = new ArrayList<>();
        List<String> linesData = ReadAndWriteData.readFile(customersFilePath);

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
        List<String> customerData = new LinkedList<>();
        customerData.add(customer.getInfo());
        ReadAndWriteData.writeToFile(customersFilePath,customerData,APPEND);
    }

    @Override
    public boolean updateCustomer(Customer updatedCustomer) {
        boolean result = false;

        if (updatedCustomer == null || updatedCustomer.getId() == null || updatedCustomer.getId().isEmpty()) {
            return false;
        }

        List<Customer> customerList = customerList();

        for (Customer customer : customerList) {
            if (customer.getId().equals(updatedCustomer.getId())) {
                if (!updatedCustomer.getName().isEmpty()) customer.setName(updatedCustomer.getName());
                if (!updatedCustomer.getPhoneNumber().isEmpty()) customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
                if (!updatedCustomer.getEmailAddress().isEmpty()) customer.setEmailAddress(updatedCustomer.getEmailAddress());

                customer.setIndexProject(updatedCustomer.getIndexProject());
                customer.setLevel(updatedCustomer.getLevel());

                result = true;
                break;
            }
        }

        if (result) {
            // Ghi lại toàn bộ danh sách Customer đã cập nhật vào file
            List<String> updatedData = new ArrayList<>();
            for (Customer customer : customerList) {
                updatedData.add(customer.getInfo());
            }
            ReadAndWriteData.writeToFile(customersFilePath, updatedData, NOT_APPEND);
        }

        return result;
    }

    @Override
    public void deleteCustomer(String id) {
        List<Customer> customerData = customerList();
        customerData.removeIf(c -> c.getId().equals(id));
        List<String> updatedData = new ArrayList<>();
        for (Customer customer : customerData) {
            updatedData.add(customer.getInfo());
        }
        ReadAndWriteData.writeToFile(customersFilePath,updatedData,NOT_APPEND);
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

        String projectFile = projectFiles[0].getName();

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

    @Override
    public void showProject(String id) {
        Customer customer = CreateObjectByID.getCustomerByID(id);
        if (customer == null) {
            return;
        }
        int idProject = customer.getIndexProject();
        if (idProject == 0) {
            System.out.println("This customer is not assigned to any project.");
            return;
        }

        File projectFolder = new File("E:\\CS M2\\src\\repository\\project");
        File[] projectFiles = projectFolder.listFiles((dir, name) -> name.startsWith(String.format("%03d", idProject)));

        if (projectFiles == null || projectFiles.length == 0) {
            System.out.println("No project found for ID: " + idProject);
            return;
        }

        File projectFile = projectFiles[0];
        List<String> projectData = ReadAndWriteData.readFile(projectFile);
        System.out.println("\nProject Details for ID " + idProject + ":");
        for (String line : projectData) {
            System.out.println(line);
        }
    }

    @Override
    public void showCustomer(String id) {
        List<Customer> customersList = customerList();
        for (Customer customer : customersList) {
            if (customer.getId().equals(id)) {
                System.out.println("Thông tin khách hàng:");
                System.out.println("ID: " + customer.getId());
                System.out.println("Tên: " + customer.getName());
                System.out.println("Số điện thoại: " + customer.getPhoneNumber());
                System.out.println("Email: " + customer.getEmailAddress());
                System.out.println("Index Project: " + customer.getIndexProject());
                System.out.println("Level: " + customer.getLevel());
                return;
            }
        }
        System.out.println("Không tìm thấy khách hàng với ID: " + id);
    }

}
