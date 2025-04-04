package service;

import model.Employee;
import model.Orders;
import model.Project;

import java.util.List;

public interface IManageProject {
    void addProject(Project project);
    void removeProject(String filePath);
    void addProject(int idOrder, String projectName, String startDate,String expectedEndDate, String LeaderID, List<Employee> employees);
}
