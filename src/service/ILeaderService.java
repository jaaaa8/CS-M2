package service;


import model.Project;

public interface ILeaderService {
    boolean editProject(Project project, int groupIndex);
}
