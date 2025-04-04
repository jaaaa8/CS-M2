package service;

import model.Orders;
import model.Project;

public interface IManageProject {
    void addProject(Project project);
    void removeProject(String filePath);
    void addProject(Orders orders);
}
