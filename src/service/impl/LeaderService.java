package service.impl;

import model.*;
import service.IEditProject;
import service.IShowProject;
import service.IShowSalary;
import util.CreateObjectByID;
import util.ReadAndWriteData;

import java.io.File;
import java.util.List;

public class LeaderService implements IEditProject, IShowSalary, IShowProject {

    @Override
    public boolean editProject(File projectFile, Project updatedProject) {
        if (!projectFile.exists()) {
            System.err.println("Project file does not exist");
            return false;
        }

        List<String> projectData = ReadAndWriteData.readFile(projectFile);
        boolean updated = false;

        for (int i = 0; i < projectData.size(); i++) {
            String line = projectData.get(i);

            if (line.startsWith("List employees: ")) {
                // Cập nhật danh sách nhân viên
                String employeeIds = updatedProject.getEmployees().stream()
                        .map(Employee::getId)
                        .reduce((id1, id2) -> id1 + "," + id2)
                        .orElse("None");
                projectData.set(i, "List employees: " + employeeIds);
                updated = true;
            }
        }

        if (updated) {
            ReadAndWriteData.writeToFile(projectFile, projectData,false);
            System.out.println("Project updated successfully");
        } else {
            System.out.println("No changes were made to the project.");
        }

        return updated;
    }


    @Override
    public void showSalary(String id) {
        Leader leader = CreateObjectByID.getLeaderByID(id);
        if(leader == null) {
            return;
        }
        System.out.println("Luong cua ban la: "+leader.baseSalary());
    }

    @Override
    public void showProject(String id) {
        Leader leader = CreateObjectByID.getLeaderByID(id);
        if (leader == null) {
            return;
        }
        int idProject = leader.getIndexProject();
        if (idProject == 0) {
            System.out.println("This leader is not assigned to any project.");
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
