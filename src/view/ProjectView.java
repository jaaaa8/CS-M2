package view;

import model.Customer;
import model.Employee;
import model.Leader;
import model.Project;
import util.CreateObjectByID;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProjectView {
    private static final Pattern ID_PATTERN = Pattern.compile("^[A-Z]{2}\\d{7}$");
    private static final Scanner scanner = new Scanner(System.in);

    public static Project inputProject() {
        String projectName;
        String startDate;
        String expectedEndDate;
        Leader leader;
        Customer customer;
        List<Employee> employees = new LinkedList<>();
        String typeOfProject;
        boolean isPaid = false;

        try {
            System.out.print("Nhập tên dự án: ");
            projectName = scanner.nextLine();

            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            startDate = scanner.nextLine();

            System.out.print("Nhập ngày kết thúc dự kiến (dd/MM/yyyy): ");
            expectedEndDate = scanner.nextLine();

            while (true) {
                System.out.print("Nhập ID của Leader (VD: LE1234567): ");
                String leaderId = scanner.nextLine().trim();
                if (isValidId(leaderId)) {
                    leader = CreateObjectByID.getLeaderByID(leaderId);
                    if (leader != null) break;
                    else System.out.println("Không tìm thấy Leader với ID này.");
                } else {
                    System.out.println("ID không hợp lệ! Định dạng: 2 chữ in hoa + 7 số.");
                }
            }

            while (true) {
                System.out.print("Nhập ID của Customer (VD: CU1234567): ");
                String customerId = scanner.nextLine().trim();
                if (isValidId(customerId)) {
                    customer = CreateObjectByID.getCustomerByID(customerId);
                    if (customer != null) break;
                    else System.out.println("Không tìm thấy Customer với ID này.");
                } else {
                    System.out.println("ID không hợp lệ! Định dạng: 2 chữ in hoa + 7 số.");
                }
            }

            System.out.println("Nhập ID các nhân viên (VD: EM1234567) - nhập 'x' để kết thúc:");
            while (true) {
                System.out.print("ID nhân viên: ");
                String empId = scanner.nextLine().trim();
                if (empId.equalsIgnoreCase("x")) break;
                if (isValidId(empId)) {
                    Employee emp = CreateObjectByID.getEmployeeByID(empId);
                    if (emp != null) {
                        employees.add(emp);
                    } else {
                        System.out.println("Không tìm thấy nhân viên với ID này.");
                    }
                } else {
                    System.out.println("ID không hợp lệ! Định dạng: 2 chữ in hoa + 7 số.");
                }
            }

            System.out.print("Nhập loại dự án: ");
            typeOfProject = scanner.nextLine();

        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi nhập dữ liệu: " + e.getMessage());
            return null;
        }

        return new Project(projectName, startDate, expectedEndDate, leader, customer, employees, typeOfProject, isPaid);
    }

    private static boolean isValidId(String id) {
        return ID_PATTERN.matcher(id).matches();
    }
}
