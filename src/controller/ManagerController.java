package controller;

import model.Employee;
import model.Manager;
import model.Project;
import service.impl.BookingService;
import service.impl.ManagerService;
import service.impl.ProjectService;
import util.CreateObjectByID;
import view.EmployeeView;
import view.ProjectView;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ManagerController {
    public static Scanner sc = new Scanner(System.in);
    private final ManagerService managerService = new ManagerService();
    private final ProjectService projectService = new ProjectService();
    private final ProjectView projectView = new ProjectView();
    private final BookingService bookingService = new BookingService();

    public void showDashBoard(Manager manager) {
        boolean flag = true;
        while (flag) {
            System.out.println("\nChào mừng quản lí: " + manager.getName());
            System.out.println("Hãy chọn chức năng để thực hiện:");
            System.out.println("1. Tạo project thủ công");
            System.out.println("2. Tạo project từ order");
            System.out.println("3. Sửa thông tin project");
            System.out.println("4. Xóa project");
            System.out.println("5. Thêm nhân viên");
            System.out.println("6. Sửa thông tin nhân viên");
            System.out.println("7. Xóa nhân viên");
            System.out.println("8. Xem lương");
            System.out.println("9. Thoát");
            System.out.print("Nhập lựa chọn: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    createProjectManually();
                    break;
                case "2":
                    createProjectFromOrder();
                    break;
                case "3":
                    editProject();
                    break;
                case "4":
                    deleteProject();
                    break;
                case "5":
                    addEmployee();
                    break;
                case "6":
                    updateEmployee();
                    break;
                case "7":
                    deleteEmployee();
                    break;
                case "8":
                    managerService.showSalary(manager.getId());
                    break;
                case "9":
                    flag = false;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void createProjectManually() {
        Project project = projectView.inputProject();
        if (project != null) {
            projectService.addProject(project);
        } else {
            System.out.println("Tạo project thất bại.");
        }
    }

    private void createProjectFromOrder() {
        System.out.println("Danh sách orders có sẵn:");
        bookingService.showAllOrder();

        System.out.print("Nhập ID của Order muốn tạo project: ");
        try {
            int idOrder = Integer.parseInt(sc.nextLine());
            System.out.print("Nhập tên project: ");
            String projectName = sc.nextLine();
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            String startDate = sc.nextLine();
            System.out.print("Nhập ngày kết thúc dự kiến (dd/MM/yyyy): ");
            String expectedEndDate = sc.nextLine();
            System.out.print("Nhập ID Leader: ");
            String leaderID = sc.nextLine();

            // Danh sách nhân viên
            System.out.println("Nhập ID các nhân viên (nhập 'x' để kết thúc):");
            List<Employee> employees = new java.util.ArrayList<>();
            while (true) {
                System.out.print("ID: ");
                String id = sc.nextLine();
                if (id.equalsIgnoreCase("x")) break;
                model.Employee emp = CreateObjectByID.getEmployeeByID(id);
                if (emp != null) {
                    employees.add(emp);
                } else {
                    System.out.println("Không tìm thấy nhân viên với ID này.");
                }
            }

            projectService.addProject(idOrder, projectName, startDate, expectedEndDate, leaderID, employees);

        } catch (Exception e) {
            System.err.println("Lỗi khi tạo project từ order: " + e.getMessage());
        }
    }

    private void editProject() {
        projectService.showAllProjects();
        System.out.print("Nhập tên file project cần sửa (VD: Project1.csv): ");
        String fileName = sc.nextLine();
        File projectFile = new File("E:\\CS M2\\src\\repository\\project\\" + fileName);
        Project updatedProject = projectView.inputProject();
        if (updatedProject != null) {
            projectService.editProject(projectFile, updatedProject);
        } else {
            System.out.println("Không thể cập nhật project.");
        }
    }

    private void deleteProject() {
        projectService.showAllProjects();
        System.out.print("Nhập tên file project cần xóa (VD: Project1.csv): ");
        String filePath = "E:\\CS M2\\src\\repository\\project\\" + sc.nextLine();
        projectService.removeProject(filePath);
    }

    private void addEmployee() {
        EmployeeView employeeView = new EmployeeView();
        managerService.addEmployee(employeeView.inputPerson());
    }

    private void updateEmployee() {
        System.out.print("Nhập ID nhân viên cần cập nhật: ");
        String id = sc.nextLine();
        boolean success = managerService.updateEmployee(id);
        if (success) {
            System.out.println("Cập nhật thành công.");
        } else {
            System.out.println("Không tìm thấy nhân viên để cập nhật.");
        }
    }

    private void deleteEmployee() {
        System.out.print("Nhập ID nhân viên cần xóa: ");
        String id = sc.nextLine();
        managerService.deleteEmployee(id);
        System.out.println("Xóa nhân viên thành công nếu ID hợp lệ.");
    }
}
