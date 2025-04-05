package controller;

import model.Employee;
import service.impl.EmployeeService;

import java.util.Scanner;

public class EmployeeController {
    public static Scanner sc = new Scanner(System.in);
    private EmployeeService employeeService = new EmployeeService();

    public void showDashBoard(Employee employee) {
        boolean flag = true;
        while (flag) {
            System.out.println("\nChào mừng nhân viên: " + employee.getName());
            System.out.println("Hãy chọn chức năng để thực hiện:");
            System.out.println("1. Xem project.");
            System.out.println("2. Xem lương.");
            System.out.println("3. Thoát.");
            System.out.print("Nhập lựa chọn: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        employeeService.showProject(employee.getId());
                        break;
                    case 2:
                        employeeService.showSalary(employee.getId());
                        break;
                    case 3:
                        System.out.println("Thoát khỏi dashboard nhân viên.");
                        flag = false;
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ.");
            }
        }
    }
}
