package service.impl;

import model.*;
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
    private static final File projectFolder = new File(projectFolderPath);

    private final BookingService bookingService = new BookingService();

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

    @Override
    public void addProject(int idOrder, String projectName, String startDate,String expectedEndDate, String LeaderID, List<Employee> employees) {
        Orders order = CreateObjectByID.getOrdersByID(idOrder);
        if (order == null) {
            System.err.println("Order does not exist");
            return;
        }
        Leader leader = CreateObjectByID.getLeaderByID(LeaderID);
        int projectID = CreateProjectFileData.getLatestIndex(projectFolder) + 1;
        Project project = new Project(projectID, projectName,startDate,expectedEndDate,leader,order.getCustomer(),employees,order.getTypeOfOrder(),false);
        CreateProjectFileData.createProjectFile(projectFolderPath, project);
        bookingService.removeBooking(idOrder);
    }

    @Override
    public List<Project> projectList() {
        List<Project> projectList = new ArrayList<>();

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
            if (projectData.size() < 9) { // Đảm bảo file có đủ dữ liệu
                System.err.println("Skipping invalid file: " + projectFile.getName());
                continue;
            }

            try {
                // Trích xuất dữ liệu từ file
                int indexID = Integer.parseInt(projectData.get(0).replace("ID: ", "").trim());
                String projectName = projectData.get(1).replace("Project Name: ", "").trim();
                String customerId = projectData.get(2).replace("Customer: ", "").trim();
                String typeOfProject = projectData.get(3).replace("Project Type: ", "").trim();
                String leaderId = projectData.get(4).replace("Leader: ", "").trim();

                // Lấy danh sách nhân viên
                String employeeLine = projectData.get(5).replace("List employees: ", "").trim();
                List<Employee> employees = new ArrayList<>();
                if (!employeeLine.equals("None")) {
                    String[] employeeIds = employeeLine.split(",");
                    for (String empId : employeeIds) {
                        Employee employee = CreateObjectByID.getEmployeeByID(empId.trim());
                        if (employee != null) {
                            employees.add(employee);
                        }
                    }
                }

                String startDate = projectData.get(6).replace("Start Date: ", "").trim();
                String expectedEndDate = projectData.get(7).replace("Expected End Date: ", "").trim();
                boolean isPaid = projectData.get(8).replace("Pay: ", "").trim().equals("Already.");

                // Tạo đối tượng Leader và Customer từ ID
                Leader leader = CreateObjectByID.getLeaderByID(leaderId);
                Customer customer = CreateObjectByID.getCustomerByID(customerId);

                if (leader == null || customer == null) {
                    System.err.println("Invalid leader or customer in file: " + projectFile.getName());
                    continue;
                }

                // Tạo Project từ dữ liệu
                Project project = new Project(
                        indexID,
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
                System.err.println("Error parsing file: " + projectFile.getName() + " -> " + e.getMessage());
            }
        }
        return projectList;
    }

    @Override
    public void showAllProjects() {
        List<Project> projectList = projectList();

        if (projectList.isEmpty()) {
            System.out.println("No projects found!");
            return;
        }

        System.out.println("List of Projects:");
        for (int i = 0; i < projectList.size(); i++) {
            Project project = projectList.get(i);
            System.out.println(i+1+"." + project.getProjectName().replace(".csv", ""));
        }
    }
}
