package view;

import model.Customer;

public class CustomerView extends PersonView<Customer>{
    @Override
    protected Customer createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        int level = 0;

        while (true) {
            try {
                System.out.print("Nhập level khách hàng: ");
                String input = sc.nextLine().trim(); // dùng nextLine để tránh lỗi khi dùng sau nextInt
                level = Integer.parseInt(input);
                if (level < 1 || level > 3) {
                    throw new NumberFormatException("Level phải nằm trong khoảng 1 đến 3.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: " + e.getMessage() + " Vui lòng nhập số nguyên hợp lệ.");
            }
        }

        return new Customer(name, phoneNumber, emailAddress, indexProject, level);
    }

}
