package view;

import model.Employee;
import model.Leader;

public class LeaderView extends EmployeeView {

    @Override
    protected Leader createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        Employee base = super.createPerson(name, phoneNumber, emailAddress, indexProject);
        int groupIndex = inputGroupIndex();
        return new Leader(name, phoneNumber, emailAddress, indexProject, base.getYearOfJoining(), base.getTypeOfEmployee(), groupIndex);
    }

    private int inputGroupIndex() {
        while (true) {
            try {
                System.out.print("Nhập vào nhóm quản lý: ");
                int group = Integer.parseInt(sc.nextLine().trim());
                if (group >= 0) return group;
                System.out.println("Lỗi: Group index không được âm.");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

    @Override
    public String inputEmployeeType() {
        return "LEADER";
    }
}
