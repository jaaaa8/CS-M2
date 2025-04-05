package view;

import model.Employee;
import service.impl.ManagerService;

public class EmployeeView extends PersonView<Employee> {
    private ManagerService managerService;
    @Override
    protected Employee createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        int yearOfJoining;
        String typeOfEmployee;

        while (true) {
            try {
                System.out.print("Nhập năm vào làm: ");
                String input = sc.nextLine().trim(); // dùng nextLine thay vì nextInt để tránh lỗi khi nhập chuỗi sau số
                yearOfJoining = Integer.parseInt(input);
                if (yearOfJoining < 1900 || yearOfJoining > 2100) {
                    throw new NumberFormatException("Năm không hợp lệ!");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: " + e.getMessage() + " Vui lòng nhập số nguyên hợp lệ.");
            }
        }

        while (true) {
            try {
                System.out.println("Chọn loại nhân viên:");
                System.out.println("1. EMPLOYEE");
                System.out.println("2. LEADER");
                System.out.println("3. MANAGER");
                System.out.print("Lựa chọn của bạn: ");
                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1" -> typeOfEmployee = "EMPLOYEE";
                    case "2" -> typeOfEmployee = "LEADER";
                    case "3" -> typeOfEmployee = "MANAGER";
                    default -> throw new Exception("Lựa chọn không hợp lệ. Vui lòng chọn 1, 2 hoặc 3.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }

        Employee employee = new Employee(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee);

        managerService.addEmployee(employee);

        return employee;
    }

}
