package view;

import model.Employee;
import model.Manager;
import service.impl.ManagerService;
import util.ReadAndWriteData;

import java.io.File;

public class ManagerView extends EmployeeView{
    private ManagerService managerService = new ManagerService();

    @Override
    public Manager inputPerson() {
        System.out.print("Nhập tên: ");
        String name = sc.nextLine();

        System.out.print("Nhập số điện thoại: ");
        String phone = sc.nextLine();

        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        int indexProject;
        while (true) {
            try {
                System.out.print("Nhập mã dự án: ");
                indexProject = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Nhập số nguyên hợp lệ.");
            }
        }

        return createPerson(name, phone, email, indexProject);
    }


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
        Manager manager = new Manager(
                name,
                phoneNumber,
                emailAddress,
                indexProject,
                employee.getYearOfJoining(),
                employee.getTypeOfEmployee(),
                experienceYear
        );

        managerService.addEmployee(manager);

        return manager;
    }

}
