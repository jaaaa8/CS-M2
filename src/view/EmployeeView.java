package view;

import model.Employee;

public class EmployeeView extends PersonView<Employee> {
    @Override
    protected Employee createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        System.out.println("Nhập năm vào làm: ");
        int yearOfJoining = sc.nextInt();
        System.out.println("Nhập loại nhân viên: ");
        String typeOfEmployee = sc.nextLine();
        return new Employee(name,phoneNumber,emailAddress,indexProject,yearOfJoining,typeOfEmployee);
    }
}
