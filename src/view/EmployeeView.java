package view;

import model.Employee;

public class EmployeeView extends PersonView<Employee> {
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
                System.out.print("Nhập loại nhân viên: ");
                typeOfEmployee = sc.nextLine().trim();
                if (typeOfEmployee.isEmpty()) {
                    throw new Exception("Loại nhân viên không được để trống.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }

        return new Employee(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee);
    }

}
