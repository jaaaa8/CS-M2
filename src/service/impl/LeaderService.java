package service.impl;

import model.Employee;

import model.Project;
import service.IEditProject;
import util.ReadAndWriteData;

import java.io.File;
import java.util.List;

public class LeaderService extends ShowProject implements IEditProject{

    @Override
    public boolean editProject(String filePath, Project updatedProject) {
        File projectFile = new File(filePath);
        if (!projectFile.exists()) {
            System.err.println("Project file does not exist: " + filePath);
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
            System.out.println("Project updated successfully: " + filePath);
        } else {
            System.out.println("No changes were made to the project.");
        }

        return updated;
    }
}
