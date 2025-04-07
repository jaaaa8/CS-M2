package controller;

import dto.CreateObjectByID;
import model.Employee;
import model.Leader;
import model.Project;
import service.LeaderService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LeaderController {
    public static Scanner scanner = new Scanner(System.in);
    private LeaderService leaderService = new LeaderService();

    public void showDashBoard(Leader leader) {
        boolean flag = true;
        while (flag) {
            System.out.println("\nChào mừng trưởng nhóm: " + leader.getName());
            System.out.println("Hãy chọn chức năng để thực hiện:");
            System.out.println("1. Xem project.");
            System.out.println("2. Sửa project.");
            System.out.println("3. Xem lương.");
            System.out.println("4. Thoát.");
            System.out.print("Nhập lựa chọn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        leaderService.showProject(leader.getId());
                        break;
                    case 2:
                        editProjectFlow(leader);
                        break;
                    case 3:
                        leaderService.showSalary(leader.getId());
                        break;
                    case 4:
                        flag = false;
                        System.out.println("Thoát khỏi dashboard trưởng nhóm.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    private void editProjectFlow(Leader leader) {
        int idProject = leader.getIndexProject();
        if (idProject == 0) {
            System.out.println("Bạn chưa được phân vào project nào.");
            return;
        }

        File projectFolder = new File("E:\\CS M2\\src\\repository\\project");
        File[] projectFiles = projectFolder.listFiles((dir, name) -> name.startsWith(String.format("%03d", idProject)));

        if (projectFiles == null || projectFiles.length == 0) {
            System.out.println("Không tìm thấy file project.");
            return;
        }

        String projectFile = projectFiles[0].getName();

        System.out.println("Nhập danh sách ID nhân viên mới (phân cách bởi dấu phẩy): ");
        String[] ids = scanner.nextLine().split(",");
        List<Employee> employeeList = new ArrayList<>();

        for (String id : ids) {
            id = id.trim();
            Employee employee = CreateObjectByID.getEmployeeByID(id);
            if (employee != null) {
                employeeList.add(employee);
            } else {
                System.out.println("Không tìm thấy nhân viên với ID: " + id);
            }
        }

        if (employeeList.isEmpty()) {
            System.out.println("Không có nhân viên hợp lệ được thêm vào project.");
            return;
        }

        Project updatedProject = new Project();
        updatedProject.setEmployees(employeeList);
        boolean success = leaderService.editProject(projectFile, updatedProject);

        if (success) {
            System.out.println("Cập nhật project thành công!");
        } else {
            System.out.println("Cập nhật thất bại.");
        }
    }
}
