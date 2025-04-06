package view;

import model.Employee;
import model.Manager;

public class ManagerView extends EmployeeView {

    @Override
    protected Manager createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        Employee base = super.createPerson(name, phoneNumber, emailAddress, indexProject);
        int expYear = inputExperienceYears();
        return new Manager(name, phoneNumber, emailAddress, indexProject, base.getYearOfJoining(), base.getTypeOfEmployee(), expYear);
    }

    private int inputExperienceYears() {
        while (true) {
            try {
                System.out.print("Nhập vào năm kinh nghiệm: ");
                int years = Integer.parseInt(sc.nextLine().trim());
                if (years >= 0) return years;
                System.out.println("Lỗi: Năm kinh nghiệm không được âm.");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

    @Override
    public String inputEmployeeType() {
        return "MANAGER";
    }
}
