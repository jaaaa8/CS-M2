package service.impl;

import model.Person;
import service.IShowProject;
import util.CreateObjectByID;
import util.ReadAndWriteData;

import java.io.File;
import java.util.List;

public abstract class ShowProject implements IShowProject {

    @Override
    public void showProject(String id) {
        Person person = CreateObjectByID.getPersonByID(id);
        if (person == null) {
            return;
        }
        int idProject = person.getIndexProject();
        if (idProject == 0) {
            System.out.println("This person is not assigned to any project.");
            return;
        }

        File projectFolder = new File("E:\\CS M2\\src\\repository\\project");
        File[] projectFiles = projectFolder.listFiles((dir, name) -> name.startsWith(String.format("%03d", idProject)));

        if (projectFiles == null || projectFiles.length == 0) {
            System.out.println("No project found for ID: " + idProject);
            return;
        }

        File projectFile = projectFiles[0];
        List<String> projectData = ReadAndWriteData.readFile(projectFile);
        System.out.println("\nProject Details for ID " + idProject + ":");
        for (String line : projectData) {
            System.out.println(line);
        }
    }
}
