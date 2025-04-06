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

    protected String inputEmployeeType() {
        return "EMPLOYEE";
    }

}
