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
    private static final Pattern ID_PATTERN = Pattern.compile("^[A-Z]\\d{10}$");
    private static final Scanner scanner = new Scanner(System.in);

    public Project inputProject() {
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
                System.out.print("Nhập ID của Leader (VD: L1234567891): ");
                String leaderId = scanner.nextLine().trim();
                if (isValidId(leaderId)) {
                    leader = CreateObjectByID.getLeaderByID(leaderId);
                    if (leader != null) break;
                    else System.out.println("Không tìm thấy Leader với ID này.");
                } else {
                    System.out.println("ID không hợp lệ! Định dạng: 1 chữ in hoa + 10 số.");
                }
            }

            while (true) {
                System.out.print("Nhập ID của Customer (VD: C1234567891): ");
                String customerId = scanner.nextLine().trim();
                if (isValidId(customerId)) {
                    customer = CreateObjectByID.getCustomerByID(customerId);
                    if (customer != null) break;
                    else System.out.println("Không tìm thấy Customer với ID này.");
                } else {
                    System.out.println("ID không hợp lệ! Định dạng: 1 chữ in hoa + 10 số.");
                }
            }

            System.out.println("Nhập ID các nhân viên (VD: E1234567971) - nhập 'x' để kết thúc:");
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
                    System.out.println("ID không hợp lệ! Định dạng: 1 chữ in hoa + 10 số.");
                }
            }

            System.out.println("Nhập loại dự án:");
            System.out.println("1. Mở rộng.");
            System.out.println("2. Khởi công.");
            System.out.println("3. Sửa chữa.");

            try {
                int projectChoice = Integer.parseInt(scanner.nextLine());
                typeOfProject = switch (projectChoice) {
                    case 1 -> "EXPAND";
                    case 2 -> "COMMENCEMENT";
                    case 3 -> "RENOVATION";
                    default -> {
                        System.out.println("Hãy nhập đúng lệnh!");
                        yield "UNKNOWN";
                    }
                };
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                typeOfProject = "UNKNOWN";
            }

        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi nhập dữ liệu: " + e.getMessage());
            return null;
        }

        Project project = new Project(projectName, startDate, expectedEndDate, leader, customer, employees, typeOfProject, isPaid);

        customer.setIndexProject(project.getIdProject());
        leader.setIndexProject(project.getIdProject());
        for (Employee employee : employees) {
            employee.setIndexProject(project.getIdProject());
        }
        return project;
    }

    public static boolean isValidId(String id) {
        return ID_PATTERN.matcher(id).matches();
    }
}
