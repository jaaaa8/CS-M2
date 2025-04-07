package controller;

import model.Customer;
import model.Orders;
import service.BookingService;
import service.CustomerService;
import dto.CreateObjectByID;
import view.BookingView;
import view.ProjectView;

import java.util.Scanner;

public class CustomerController{
    public static Scanner sc = new Scanner(System.in);
    private CustomerService customerService = new CustomerService();
    private BookingService bookingService = new BookingService();
    private BookingView bookingView = new BookingView();


    public void showDashBoard(Customer customer) {
        boolean flag = true;
        while (flag) {
            System.out.println("\nChào mừng khách hàng: " + customer.getName());
            System.out.println("Hãy chọn chức năng để thực hiện:");
            System.out.println("1. Tạo order.");
            System.out.println("2. Xóa order.");
            System.out.println("3. Sửa order.");
            System.out.println("4. Xem project.");
            System.out.println("5. Thanh toán.");
            System.out.println("6. Sửa thông tin cá nhân.");
            System.out.println("7. Thoát.");
            System.out.print("Nhập lựa chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
                continue;
            }

            switch (choice) {
                case 1:
                    Orders newOrder = bookingView.inputOrder(customer);
                    bookingService.addBooking(newOrder);
                    System.out.println("Đã tạo order thành công.");
                    break;

                case 2:
                    System.out.print("Nhập ID order cần xóa: ");
                    String removeIdStr = sc.nextLine();
                    try {
                        int removeId = Integer.parseInt(removeIdStr);
                        bookingService.removeBooking(removeId);
                        System.out.println("Đã xóa order.");
                    } catch (NumberFormatException e) {
                        System.out.println("ID phải là số!");
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID order cần cập nhật: ");
                    String updateIdStr = sc.nextLine();
                    try {
                        int updateId = Integer.parseInt(updateIdStr);
                        boolean updated = bookingService.updateBooking(updateId);
                        if (updated) {
                            System.out.println("Cập nhật order thành công.");
                        } else {
                            System.out.println("Không tìm thấy order với ID này.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ID phải là số!");
                    }
                    break;

                case 4:
                    System.out.print("Nhập ID khách hàng để xem project: ");
                    String idToShow = sc.nextLine();
                    if (ProjectView.isValidId(idToShow)) {
                        customerService.showProject(idToShow);
                    } else {
                        System.out.println("ID không hợp lệ! Định dạng: 2 chữ in hoa + 7 số.");
                    }
                    break;

                case 5:
                    System.out.print("Bạn có chắc chắn muốn thanh toán không?");
                    String idToPay = sc.nextLine();
                    if (ProjectView.isValidId(idToPay)) {
                        Customer ctm = CreateObjectByID.getCustomerByID(idToPay);
                        if (ctm != null) {
                            customerService.pay(ctm);
                        } else {
                            System.out.println("Không tìm thấy khách hàng.");
                        }
                    } else {
                        System.out.println("ID không hợp lệ! Định dạng: 2 chữ in hoa + 7 số.");
                    }
                    break;

                case 6:
                    System.out.println("Chỉnh sửa thông tin cá nhân:");
                    customerService.showCustomer(customer.getId());

                    try {
                        System.out.println("Sửa tên " + customer.getName() + " thành:");
                        String newName = sc.nextLine();
                        if (!newName.matches("^[\\p{L} .'-]+$")) {
                            throw new IllegalArgumentException("Tên không hợp lệ! Chỉ được chứa chữ cái và khoảng trắng.");
                        }

                        System.out.println("Sửa SDT " + customer.getPhoneNumber() + " thành: ");
                        String newPhone = sc.nextLine();
                        if (!newPhone.matches("^[0-9]{10,11}$")) {
                            throw new IllegalArgumentException("Số điện thoại không hợp lệ! Phải có 10-11 chữ số.");
                        }

                        System.out.println("Sửa email " + customer.getEmailAddress() + " thành: ");
                        String newEmail = sc.nextLine();
                        if (!newEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                            throw new IllegalArgumentException("Email không đúng định dạng!");
                        }

                        customer.setName(newName);
                        customer.setPhoneNumber(newPhone);
                        customer.setEmailAddress(newEmail);
                        customerService.updateCustomer(customer);
                        System.out.println("Cập nhật thông tin thành công!");
                        flag = false;

                    } catch (IllegalArgumentException e) {
                        System.out.println("Lỗi: " + e.getMessage());
                    }
                    break;


                case 7:
                    System.out.println("Thoát...");
                    flag = false;
                    break;

                default:
                    System.out.println("Vui lòng chọn đúng tùy chọn.");
            }
        }
    }
}
