package service.impl;

import model.Employee;
import service.IEmployeeService;

import java.io.File;

public class EmployeeService extends ShowProject implements IEmployeeService {
    private static final File customers = new File("E:\\CS M2\\src\\repository\\customers.csv");


    @Override
    public void showSalary(Employee employee) {
        System.out.println("Luong cua ban la: "+employee.baseSalary());
    }
}
