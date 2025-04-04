package controller;

import model.Customer;
import service.impl.BookingService;
import service.impl.CustomerService;

import java.util.Scanner;

public class CustomerController {
    public static Scanner sc = new Scanner(System.in);
    private CustomerService customerService = new CustomerService();
    private BookingService bookingService = new BookingService();

    public void showDashBoard(Customer customer) {
        boolean flag = true;
        while (flag) {
            System.out.println("Chào mừng khách hàng: "+customer.getName());
            System.out.println("Hãy chọn chức năng để thực hiện:" +
                    "\n1. Tạo order." +
                    "\n2. Xóa order." +
                    "\n3. Sửa order." +
                    "\n4. Xem project." +
                    "\n5. Thanh toán." +
                    "\n6. Thoát.");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:

            }
        }
    }
}
