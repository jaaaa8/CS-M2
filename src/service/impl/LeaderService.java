package service.impl;

import model.Employee;
import model.Leader;
import model.Orders;
import model.Project;
import service.IEditProject;
import service.IShowSalary;
import util.CreateObjectByID;
import util.ReadAndWriteData;

import java.io.File;
import java.util.List;

public class LeaderService extends ShowProject implements IEditProject, IShowSalary {
    private final BookingService bookingService = new BookingService();

    @Override
    public boolean editProject(File projectFile, Project updatedProject) {
        if (!projectFile.exists()) {
            System.err.println("Project file does not exist");
            return false;
        }

        List<String> projectData = ReadAndWriteData.readFile(projectFile);
        boolean updated = false;

        for (int i = 0; i < projectData.size(); i++) {
            String line = projectData.get(i);

            if (line.startsWith("List employees: ")) {
                // Cập nhật danh sách nhân viên
                String employeeIds = updatedProject.getEmployees().stream()
                        .map(Employee::getId)
                        .reduce((id1, id2) -> id1 + "," + id2)
                        .orElse("None");
                projectData.set(i, "List employees: " + employeeIds);
                updated = true;
            }
        }

        if (updated) {
            ReadAndWriteData.writeToFile(projectFile, projectData,false);
            System.out.println("Project updated successfully");
        } else {
            System.out.println("No changes were made to the project.");
        }

        return updated;
    }


    @Override
    public void showSalary(String id) {
        Leader leader = CreateObjectByID.getLeaderByID(id);
        if(leader == null) {
            return;
        }
        System.out.println("Luong cua ban la: "+leader.baseSalary());
    }

    public void addBooking(Orders orders) {
        bookingService.addBooking(orders);
    }
}
