package service;

import model.Project;

import java.io.File;

public interface IEditProject {
    boolean editProject(File projectFile, Project project);
}
