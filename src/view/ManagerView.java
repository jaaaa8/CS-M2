package view;

import model.Employee;
import model.Manager;

public class ManagerView extends EmployeeView{
    @Override
    protected Manager createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        Employee employee = super.createPerson(name, phoneNumber, emailAddress, indexProject);
        System.out.println("Nhập vào năm kinh nghiệm: ");
        int experienceYear = sc.nextInt();
        sc.nextLine();
        return new Manager(name, phoneNumber, emailAddress, indexProject, employee.getYearOfJoining(), employee.getTypeOfEmployee(), experienceYear);
    }
}
