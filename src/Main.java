import controller.CustomerController;
import controller.EmployeeController;
import controller.LeaderController;
import controller.ManagerController;
import model.Customer;
import model.Employee;
import model.Leader;
import model.Manager;
import service.CustomerService;
import service.ManagerService;
import view.CustomerView;
import view.EmployeeView;
import view.LeaderView;
import view.ManagerView;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    private static ManagerService managerService = new ManagerService();
    private static CustomerService customerService = new CustomerService();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== Chào mừng đến với phần mềm quản lí dự án! =====");
            System.out.println("Bạn là:");
            System.out.println("1. Nhân viên.");
            System.out.println("2. Khách hàng.");
            System.out.println("3. Leader.");
            System.out.println("4. Manager.");
            System.out.println("5. Thoát chương trình!");
            System.out.print("Nhập lựa chọn: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> {
                        EmployeeView employeeView = new EmployeeView();
                        Employee emp = employeeView.inputPerson();
                        managerService.addEmployee(emp);
                        EmployeeController empController = new EmployeeController();
                        empController.showDashBoard(emp);
                    }

                    case 2 -> {
                        CustomerView customerView = new CustomerView();
                        Customer customer = customerView.inputPerson();
                        customerService.addCustomer(customer);
                        CustomerController customerController = new CustomerController();
                        customerController.showDashBoard(customer);
                    }

                    case 3 -> {
                        LeaderView leaderView = new LeaderView();
                        Leader leader = (Leader) leaderView.inputPerson();
                        managerService.addEmployee(leader);
                        LeaderController leaderController = new LeaderController();
                        leaderController.showDashBoard(leader);
                    }

                    case 4 -> {
                        ManagerView managerView = new ManagerView();
                        Manager manager = (Manager) managerView.inputPerson();
                        managerService.addEmployee(manager);
                        ManagerController managerController = new ManagerController();
                        managerController.showDashBoard(manager);
                    }

                    case 5 -> System.out.println("Thoát chương trình. Hẹn gặp lại!");

                    default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập số từ 1 đến 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ!");
                choice = 0;
            }

        } while (choice != 5);
    }
}
