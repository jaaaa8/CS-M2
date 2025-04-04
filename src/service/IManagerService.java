package service;

import model.Employee;

import java.util.List;

public interface IManagerService {
    List<Employee> employeeList();
    void addEmployee(Employee employee);
    void deleteEmployee(String id);
    boolean updateEmployee(String id);
    void promoteEmployee(String id);
    void demoteEmployee(String id);
}
