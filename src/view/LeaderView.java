package view;

import model.Employee;
import model.Leader;

public class LeaderView extends EmployeeView {
    @Override
    protected Leader createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        Employee employee = super.createPerson(name, phoneNumber, emailAddress, indexProject);
        System.out.println("Nhập vào STT nhóm: ");
        int groupIndex = sc.nextInt();
        sc.nextLine();
        return new Leader(name,phoneNumber,emailAddress,indexProject,employee.getYearOfJoining(),employee.getTypeOfEmployee(),groupIndex);
    }
}
