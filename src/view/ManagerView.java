package view;

import model.Employee;
import model.Manager;

public class ManagerView extends EmployeeView{
    @Override
    protected Manager createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        Employee employee = super.createPerson(name, phoneNumber, emailAddress, indexProject);
        int experienceYear;

        while (true) {
            try {
                System.out.print("Nhập vào năm kinh nghiệm: ");
                String input = sc.nextLine().trim();
                experienceYear = Integer.parseInt(input);
                if (experienceYear < 0) {
                    throw new NumberFormatException("Năm kinh nghiệm không được âm.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: " + e.getMessage() + " Vui lòng nhập số nguyên hợp lệ.");
            }
        }

        return new Manager(
                name,
                phoneNumber,
                emailAddress,
                indexProject,
                employee.getYearOfJoining(),
                employee.getTypeOfEmployee(),
                experienceYear
        );
    }

}
