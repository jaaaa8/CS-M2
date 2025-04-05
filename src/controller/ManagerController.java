package controller;

import model.Employee;
import model.Manager;
import model.Project;
import service.impl.*;
import util.CreateObjectByID;
import view.EmployeeView;
import view.LeaderView;
import view.ManagerView;
import view.ProjectView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerController {
    public static Scanner sc = new Scanner(System.in);
    private final ManagerService managerService = new ManagerService();
    private final ProjectService projectService = new ProjectService();
    private final ProjectView projectView = new ProjectView();
    private final BookingService bookingService = new BookingService();
    private final CustomerService customerService = new CustomerService();
    private static final String projectFolderPath = "E:\\CS M2\\src\\repository\\project";

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
            System.out.println("8. Xóa khách hàng");
            System.out.println("9. Xem lương");
            System.out.println("10. Thoát");
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
                    deleteCustomer();
                    break;
                case "10":
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

        System.out.print("Nhập ID của Order muốn tạo project (10 chữ số): ");
        try {
            String orderInput = sc.nextLine();
            if (!orderInput.matches("\\d{10}")) {
                System.err.println("Order ID phải là số nguyên có đúng 10 chữ số.");
                return;
            }
            int idOrder = Integer.parseInt(orderInput);

            System.out.print("Nhập tên project: ");
            String projectName = sc.nextLine();

            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            String startDate = sc.nextLine();
            if (!startDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.err.println("Ngày bắt đầu không đúng định dạng (dd/MM/yyyy).");
                return;
            }

            System.out.print("Nhập ngày kết thúc dự kiến (dd/MM/yyyy): ");
            String expectedEndDate = sc.nextLine();
            if (!expectedEndDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                System.err.println("Ngày kết thúc không đúng định dạng (dd/MM/yyyy).");
                return;
            }

            System.out.print("Nhập ID Leader (VD: LE1234567): ");
            String leaderID = sc.nextLine();
            if (!ProjectView.isValidId(leaderID)) {
                System.err.println("Leader ID không hợp lệ. Phải có 2 chữ viết hoa và 7 số nguyên đằng sau.");
                return;
            }

            System.out.println("Nhập ID các nhân viên (nhập 'x' để kết thúc):");
            List<Employee> employees = new ArrayList<>();
            while (true) {
                System.out.print("ID: ");
                String id = sc.nextLine();
                if (id.equalsIgnoreCase("x")) break;

                if (!ProjectView.isValidId(id)) {
                    System.err.println("ID nhân viên không hợp lệ. Phải có 2 chữ viết hoa và 7 số nguyên đằng sau. Bỏ qua.");
                    continue;
                }

                Employee emp = CreateObjectByID.getEmployeeByID(id);
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
        try {
            projectService.showAllProjects();
            System.out.print("Nhập tên file project cần sửa (VD: 001EXJACK.csv): ");
            String fileName = sc.nextLine().trim();

            if (!fileName.matches("\\d{3}[A-Z]+\\.csv")) {
                System.err.println("Tên file không hợp lệ. Định dạng đúng là 3 số đầu + chữ IN HOA + '.csv'.");
                return;
            }

            File projectFile = new File(projectFolderPath + fileName);
            if (!projectFile.exists()) {
                System.err.println("File không tồn tại: " + projectFile.getName());
                return;
            }

            Project updatedProject = projectView.inputProject();
            if (updatedProject != null) {
                projectService.editProject(projectFile, updatedProject);
            } else {
                System.out.println("Không thể cập nhật project.");
            }
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi chỉnh sửa project: " + e.getMessage());
        }
    }


    private void deleteProject() {
        try {
            projectService.showAllProjects();
            System.out.print("Nhập tên file project cần xóa (VD: 001EXJACK.csv): ");
            String fileName = sc.nextLine().trim();

            if (!fileName.matches("\\d{3}[A-Z]+\\.csv")) {
                System.err.println("Tên file không hợp lệ. Định dạng đúng là: 3 số + chữ IN HOA + '.csv'.");
                return;
            }

            File fileToDelete = new File(projectFolderPath + fileName);
            if (!fileToDelete.exists()) {
                System.err.println("File không tồn tại: " + fileToDelete.getName());
                return;
            }

            projectService.removeProject(fileToDelete.getAbsolutePath());
            System.out.println("Đã xóa file project: " + fileName);
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi xóa project: " + e.getMessage());
        }
    }


    private void addEmployee() {
        System.out.println("=== Add New Employee ===");
        System.out.println("1. Thêm nhân viên.");
        System.out.println("2. Thêm trưởng nhóm.");
        System.out.println("3. Thêm quản lí.");
        System.out.print("Nhập lựa chọn: ");

        String choice = sc.nextLine();

        switch (choice) {
            case "1" -> {
                EmployeeView employeeView = new EmployeeView();
                managerService.addEmployee(employeeView.inputPerson());
            }
            case "2" -> {
                LeaderView leaderView = new LeaderView();
                managerService.addEmployee(leaderView.inputPerson());
            }
            case "3" -> {
                ManagerView managerView = new ManagerView();
                managerService.addEmployee(managerView.inputPerson());
            }
            default -> System.out.println("Invalid choice. Please select 1, 2 or 3.");
        }
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
        try {
            System.out.print("Nhập ID nhân viên cần xóa: ");
            String id = sc.nextLine();

            if (!ProjectView.isValidId(id)) {
                System.err.println("ID không hợp lệ. Phải gồm 2 chữ viết hoa + 7 số.");
                return;
            }

            managerService.deleteEmployee(id);
            System.out.println("Xóa nhân viên thành công!");
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi xóa nhân viên: " + e.getMessage());
        }
    }

    private void deleteCustomer() {
        try {
            System.out.print("Nhập ID khách hàng muốn xóa: ");
            String id = sc.nextLine();

            if (!ProjectView.isValidId(id)) {
                System.err.println("ID không hợp lệ. Phải gồm 2 chữ viết hoa + 7 số.");
                return;
            }

            customerService.deleteCustomer(id);
            System.out.println("Xóa khách hàng thành công!");
        } catch (Exception e) {
            System.err.println("Đã xảy ra lỗi khi xóa khách hàng: " + e.getMessage());
        }
    }

}
