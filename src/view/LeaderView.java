package view;

import model.Employee;
import model.Leader;

public class LeaderView extends EmployeeView {
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

        return new Leader(
                name,
                phoneNumber,
                emailAddress,
                indexProject,
                employee.getYearOfJoining(),
                employee.getTypeOfEmployee(),
                groupIndex
        );
    }

}
