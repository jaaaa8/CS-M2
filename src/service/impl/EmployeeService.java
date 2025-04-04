package service.impl;

import model.Employee;
import model.Orders;
import model.Person;
import service.IShowProject;
import service.IShowSalary;
import util.CreateObjectByID;
import util.ReadAndWriteData;

import java.io.File;
import java.util.List;

public class EmployeeService implements IShowSalary, IShowProject {
    private final BookingService bookingService = new BookingService();

    @Override
    public void showSalary(String id) {
        Employee employee = CreateObjectByID.getEmployeeByID(id);
        if (employee == null) {
            return;
        }
        System.out.println("Luong cua ban la: "+employee.baseSalary());
    }

    public void addBooking(Orders orders) {
        bookingService.addBooking(orders);
    }

    @Override
    public void showProject(String id) {
        Employee employee = CreateObjectByID.getEmployeeByID(id);
        if (employee == null) {
            return;
        }
        int idProject = employee.getIndexProject();
        if (idProject == 0) {
            System.out.println("This employee is not assigned to any project.");
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
}
