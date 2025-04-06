package view;

import model.Customer;

public class CustomerView extends PersonView<Customer> {

    @Override
    protected Customer createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        int level = inputCustomerLevel();
        return new Customer(name, phoneNumber, emailAddress, indexProject, level);
    }

    private int inputCustomerLevel() {
        while (true) {
            try {
                System.out.print("Nhập level khách hàng (1-3): ");
                int level = Integer.parseInt(sc.nextLine().trim());
                if (level >= 1 && level <= 3) return level;
                System.out.println("Lỗi: Level phải từ 1 đến 3.");
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

}
