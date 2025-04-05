package view;

import model.Employee;
import model.Leader;
import service.impl.ManagerService;

public class LeaderView extends EmployeeView {
    private ManagerService managerService = new ManagerService();

    @Override
    public Leader inputPerson() {
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
    protected Leader createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        Employee employee = super.createPerson(name, phoneNumber, emailAddress, indexProject);
        int groupIndex;

        while (true) {
            try {
                System.out.print("Nhập vào nhóm quản lý: ");
                String input = sc.nextLine().trim(); // dùng nextLine để tránh lỗi Scanner
                groupIndex = Integer.parseInt(input);
                if (groupIndex < 0) {
                    throw new NumberFormatException("Group index không được âm.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: " + e.getMessage() + " Vui lòng nhập số nguyên hợp lệ.");
            }
        }
        Leader leader = new Leader(
                name,
                phoneNumber,
                emailAddress,
                indexProject,
                employee.getYearOfJoining(),
                employee.getTypeOfEmployee(),
                groupIndex
        );

        managerService.addEmployee(leader);

        return leader;
    }

}
