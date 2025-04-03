package service.impl;

import model.Customer;
import service.ICustomerService;
import util.ReadAndWriteData;

import java.io.File;
import java.util.List;


public class CustomerService extends ShowProject implements ICustomerService {

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

        for (int i = 0; i < projectData.size(); i++) {
            if (projectData.get(i).contains("Pay: Not yet.")) {
                projectData.set(i, projectData.get(i).replace("Pay: Not yet.", "Pay: Already."));
                break;
            }
        }
    }
}
