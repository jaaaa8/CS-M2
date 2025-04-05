package view;

import model.Employee;

public class EmployeeView extends PersonView<Employee> {

    @Override
    protected Employee createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        int yearOfJoining = inputYearOfJoining();
        String type = inputEmployeeType();
        return new Employee(name, phoneNumber, emailAddress, indexProject, yearOfJoining, type);
    }

    private int inputYearOfJoining() {
        while (true) {
            try {
                System.out.print("Nhập năm vào làm: ");
                int year = Integer.parseInt(sc.nextLine().trim());
                if (year >= 1900 && year <= 2100) return year;
                System.out.println("Lỗi: Năm không hợp lệ!");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

    private String inputEmployeeType() {
        while (true) {
            System.out.println("Chọn loại nhân viên:");
            System.out.println("1. EMPLOYEE");
            System.out.println("2. LEADER");
            System.out.println("3. MANAGER");
            System.out.print("Lựa chọn của bạn: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": return "EMPLOYEE";
                case "2": return "LEADER";
                case "3": return "MANAGER";
                default: System.out.println("Lỗi: Lựa chọn không hợp lệ. Vui lòng chọn 1, 2 hoặc 3.");
            }
        }
    }
}
