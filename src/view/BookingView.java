package view;

import model.Customer;
import model.Orders;

import java.util.Scanner;

public class BookingView {
    public static Scanner scanner = new Scanner(System.in);

    public Orders inputOrder(Customer customer) {
        System.out.println("Nhập mô tả về yêu cầu: ");
        String description = scanner.nextLine();
        int choice = -1;
        String typeOfOrder = "";
        while (choice <= 0 || choice > 3) {
            System.out.println("Nhập kiểu yêu cầu: ");
            System.out.println("1. Mở rộng.");
            System.out.println("2. Khởi công.");
            System.out.println("3. Sửa chữa.");
            try{
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        typeOfOrder = "EXPAND";
                        break;
                    case 2:
                        typeOfOrder = "COMMENCEMENT";
                        break;
                    case 3:
                        typeOfOrder = "RENOVATION";
                    default:
                        System.out.println("Hãy nhập đúng lệnh!");
                }
            }catch (NumberFormatException e){
                System.out.println("Hãy nhập vào giá trị là số!");
            }
        }
        int budget;
        while(true) {
            System.out.println("Nhập mức chi cho yêu cầu: ");
            try {
                budget = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e){
                System.out.println("Hãy nhập vào giá trị là số!");
            }
        }
        return new Orders(customer,description,typeOfOrder,budget);
    }
}
