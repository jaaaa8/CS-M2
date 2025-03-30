package service;

import model.Project;

public interface IManageProject {
    boolean editProject(int indexID, Project project);
    void addProject(Project project);
    void removeProject(int indexID, Project project);
}
