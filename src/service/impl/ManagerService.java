package service.impl;

import model.Employee;
import service.IManagerService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ManagerService implements IManagerService {

    @Override
    public List<Employee> employeeList() {
        List<Employee> employees = new ArrayList<Employee>();
        //doc du lieu tu database o day roi truyen vao employees
        return employees;
    }

    @Override
    public void addEmployee(Employee employee) {
        List<String> employees = new LinkedList<>();
        employees.add(employee.getInfo());
    }

    @Override
    public void deleteEmployee(Employee employee) {
        List<String> employees = new LinkedList<>();
        employees.remove(employee.getInfo());
    }

    @Override
    public void demoteEmployee(Employee employee) {

    }

    @Override
    public void promoteEmployee(Employee employee) {

    }

    @Override
    public void updateEmployee(Employee employee) {

    }
}
