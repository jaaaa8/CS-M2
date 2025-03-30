package service;

import model.Employee;

import java.util.List;

public interface IManagerService {
    List<Employee> employeeList();
    void addEmployee(Employee employee);
    void deleteEmployee(String id, Employee employee);
    boolean updateEmployee(String id, Employee employee);
    void promoteEmployee(Employee employee);
    void demoteEmployee(Employee employee);
}
