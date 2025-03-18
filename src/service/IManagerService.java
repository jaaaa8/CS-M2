package service;

import model.Employee;

import java.util.List;

public interface IManagerService {
    List<Employee> employeeList();
    abstract void addEmployee(Employee employee);
    abstract void deleteEmployee(Employee employee);
    abstract void updateEmployee(Employee employee);
    abstract void promoteEmployee(Employee employee);
    abstract void demoteEmployee(Employee employee);
}
