package service.impl;

import model.Employee;
import model.Project;

import java.util.List;

public interface IManageProject {
    List<Project> projectList();
    void addProject(Project project);
    void removeProject(String filePath);
    void addProject(int idOrder, String projectName, String startDate,String expectedEndDate, String LeaderID, List<Employee> employees);
    void showAllProjects();
}
