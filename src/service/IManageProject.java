package service;

import model.Project;

public interface IManageProject {
    boolean editProject(String filePath, Project project);
    void addProject(Project project);
    void removeProject(String filePath, Project project);
}
