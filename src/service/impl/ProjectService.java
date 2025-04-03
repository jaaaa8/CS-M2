package service.impl;

import model.Customer;
import model.Employee;
import model.Leader;
import model.Project;
import service.IEditProject;
import service.IManageProject;
import util.CreateObjectByID;
import util.CreateProjectFileData;
import util.ReadAndWriteData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProjectService implements IManageProject, IEditProject {
    private static final String projectFolderPath = "E:\\CS M2\\src\\repository\\project";

    @Override
    public boolean editProject(File projectFile, Project updatedProject) {
        if (!projectFile.exists()) {
            System.err.println("Project file does not exist: ");
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
            } else if (line.startsWith("Pay: ")) {
                projectData.set(i, "Pay: "+ (updatedProject.isPaid() ? "Already." : "Not yet."));
                updated = true;
            }
        }

        // Nếu có thay đổi, ghi lại dữ liệu vào file
        if (updated) {
            ReadAndWriteData.writeToFile(projectFile, projectData, false);
            System.out.println("Project updated successfully");
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
    public void removeProject(String filePath) {
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

    public List<Project> projectList() {
        List<Project> projectList = new ArrayList<>();
        File projectFolder = new File("projects");

        if (!projectFolder.exists() || !projectFolder.isDirectory()) {
            System.err.println("Project directory not found!");
            return projectList;
        }

        File[] projectFiles = projectFolder.listFiles((dir, name) -> name.endsWith(".csv"));
        if (projectFiles == null || projectFiles.length == 0) {
            System.err.println("No project files found!");
            return projectList;
        }

        for (File projectFile : projectFiles) {
            List<String> projectData = ReadAndWriteData.readFile(projectFile);
            if (projectData.isEmpty()) {
                System.err.println("Skipping empty file: " + projectFile.getName());
                continue;
            }

            try {
                // Trích xuất dữ liệu từ danh sách projectData
                String projectName = projectData.get(0).replace("Project Name: ", "").trim();
                String customerId = projectData.get(1).replace("Customer: ", "").trim();
                String typeOfProject = projectData.get(2).replace("Project Type: ", "").trim();
                String leaderId = projectData.get(3).replace("Leader: ", "").trim();

                // Lấy danh sách nhân viên
                String employeeLine = projectData.get(4).replace("List employees: ", "").trim();
                List<Employee> employees = new ArrayList<>();
                if (!employeeLine.equals("None")) {
                    String[] employeeIds = employeeLine.split(",");
                    for (String empId : employeeIds) {
                        employees.add(CreateObjectByID.getEmployeeByID(empId.trim()));
                    }
                }

                String startDate = projectData.get(5).replace("Start Date: ", "").trim();
                String expectedEndDate = projectData.get(6).replace("Expected End Date: ", "").trim();
                boolean isPaid = projectData.get(7).replace("Pay: ", "").trim().equals("Already.");

                // Tạo đối tượng Leader và Customer từ ID
                Leader leader = CreateObjectByID.getLeaderByID(leaderId);
                Customer customer = CreateObjectByID.getCustomerByID(customerId);

                // Tạo Project từ dữ liệu
                Project project = new Project(
                        projectList.size() + 1,  // indexID tự tăng
                        projectName,
                        startDate,
                        expectedEndDate,
                        leader,
                        customer,
                        employees,
                        typeOfProject,
                        isPaid
                );

                // Thêm vào danh sách
                projectList.add(project);

            } catch (Exception e) {
                System.err.println("Error parsing file: " + projectFile.getName());
            }
        }
        return projectList;
    }

}
