package service.impl;

import model.Employee;
import model.Orders;
import service.IShowSalary;
import util.CreateObjectByID;

public class EmployeeService extends ShowProject implements IShowSalary {
    private final BookingService bookingService = new BookingService();

    @Override
    public void showSalary(String id) {
        Employee employee = CreateObjectByID.getEmployeeByID(id);
        if (employee == null) {
            return;
        }
        System.out.println("Luong cua ban la: "+employee.baseSalary());
    }

    public void addBooking(Orders orders) {
        bookingService.addBooking(orders);
    }
}
