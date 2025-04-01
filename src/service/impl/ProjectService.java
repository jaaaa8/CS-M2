package service.impl;

import model.Employee;
import model.Project;
import service.IManageProject;
import util.CreateProjectFileData;
import util.ReadAndWriteData;

import java.io.File;
import java.util.List;

public class ProjectService implements IManageProject {
    private static final String projectFolderPath = "E:\\CS M2\\src\\repository\\project";
    private static final boolean APPEND = true;
    private static final boolean NOT_APPEND = false;

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

            if (line.startsWith("Project Name: ")) {
                projectData.set(i, "Project Name: " + updatedProject.getProjectName());
                updated = true;
            } else if (line.startsWith("Customer: ")) {
                projectData.set(i, "Customer: " + updatedProject.getCustomer().getName().toLowerCase());
                updated = true;
            } else if (line.startsWith("Project Type: ")) {
                projectData.set(i, "Project Type: " + updatedProject.getTypeOfProject());
                updated = true;
            } else if (line.startsWith("Leader Name: ")) {
                projectData.set(i, "Leader Name: " + updatedProject.getLeader().getName());
                updated = true;
            } else if (line.startsWith("List employees: ")) {
                // Cập nhật danh sách nhân viên
                String employeeIds = updatedProject.getEmployees().stream()
                        .map(Employee::getId)
                        .reduce((id1, id2) -> id1 + "," + id2)
                        .orElse("None");
                projectData.set(i, "List employees: " + employeeIds);
                updated = true;
            } else if (line.startsWith("Start Date: ")) {
                projectData.set(i, "Start Date: " + updatedProject.getStartDate());
                updated = true;
            } else if (line.startsWith("Expected End Date: ")) {
                projectData.set(i, "Expected End Date: " + updatedProject.getExpectedEndDate());
                updated = true;
            }
        }

        // Nếu có thay đổi, ghi lại dữ liệu vào file
        if (updated) {
            ReadAndWriteData.writeToFile(projectFile, projectData, NOT_APPEND);
            System.out.println("Project updated successfully: " + filePath);
        } else {
            System.out.println("No changes were made to the project.");
        }

        return updated;
    }

    @Override
    public void addProject(Project project) {
        CreateProjectFileData.createProjectFile(projectFolderPath, project);
    }

    @Override
    public void removeProject(String filePath, Project project) {
        File projectFile = new File(filePath);

        if (projectFile.exists()) {
            if (projectFile.delete()) {
                System.out.println("Deleted project file: " + filePath);
            } else {
                System.err.println("Failed to delete project file: " + filePath);
            }
        } else {
            System.err.println("Project file not found: " + filePath);
        }
    }
}
