package service.impl;

import model.Employee;
import service.IEmployeeService;

import java.io.File;

public class EmployeeService extends ShowProject implements IEmployeeService {

    @Override
    public void showSalary(Employee employee) {
        System.out.println("Luong cua ban la: "+employee.baseSalary());
    }
}
