package service;

import model.Project;

public interface IManageProject {
    void addProject(Project project);
    void removeProject(String filePath, Project project);
}
