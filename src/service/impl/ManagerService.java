package service.impl;

import model.Employee;
import model.Leader;
import model.Manager;
import model.Project;
import service.IManageProject;
import service.IManagerService;
import util.ReadAndWriteData;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ManagerService implements IManagerService{
    private static final File employees = new File("E:\\CS M2\\src\\repository\\employees.csv");
    private static final boolean APPEND = true;
    private static final boolean NOT_APPEND = false;

    private final ProjectService projectService = new ProjectService();

    @Override
    public List<Employee> employeeList() {
        List<Employee> employeeData = new ArrayList<>();
        List<String> linesData = ReadAndWriteData.readFile(employees);

        for (String line : linesData) {
            String[] partsData = line.split(",");
            if (partsData.length < 8) {
                System.err.println("Data format exception at line " + line);
                continue;
            }
            String id = partsData[0];
            String name = partsData[1];
            String phoneNumber = partsData[2];
            String emailAddress = partsData[3];
            String typeOfEmployee = partsData[5];
            int indexProject = Integer.parseInt(partsData[4]);
            int salary = Integer.parseInt(partsData[6]);
            int yearOfJoining = Integer.parseInt(partsData[7]);
            try{
                switch (typeOfEmployee.toLowerCase()) {
                    case "employee":
                        employeeData.add(new Employee(name,phoneNumber,emailAddress,indexProject,yearOfJoining,typeOfEmployee,salary,id));
                        break;
                    case "leader":
                        int indexGroup = Integer.parseInt(partsData[8]);
                        employeeData.add(new Leader(name,phoneNumber,emailAddress,indexProject,yearOfJoining,typeOfEmployee,salary,id,indexGroup));
                        break;
                    case "manager":
                        int experienceYear = Integer.parseInt(partsData[8]);
                        employeeData.add(new Manager(name,phoneNumber,emailAddress,indexProject,yearOfJoining,typeOfEmployee,salary,id,experienceYear));
                        break;
                    default:
                        System.err.println("Invalid type of employee");
                }
            } catch (Exception e){
                System.err.println("Error reading employee data!");
            }
        }
        return employeeData;
    }

    @Override
    public void addEmployee(Employee employee) {
        List<String> employeeData = new LinkedList<>();
        employeeData.add(employee.getInfo());
        ReadAndWriteData.writeToFile(employees,employeeData,APPEND);
    }

    @Override
    public void deleteEmployee(String id, Employee employee) {
        List<Employee> employeeData = employeeList();
        employeeData.removeIf(emp -> emp.getId().equals(id));
        List<String> employeesData = new ArrayList<>();
        for (Employee emp : employeeData) {
            employeesData.add(emp.getInfo());
        }
        ReadAndWriteData.writeToFile(employees,employeesData,NOT_APPEND);
    }

    @Override
    public void demoteEmployee(Employee employee) {

    }

    @Override
    public void promoteEmployee(Employee employee) {

    }

    @Override
    public boolean updateEmployee(String id, Employee updatedEmployee) {
        boolean result = false;
        List<Employee> employeeData = employeeList();

        for (Employee emp : employeeData) {
            if (emp.getId().equals(id)) {
                // Cập nhật từng thuộc tính nếu có giá trị mới
                if (!updatedEmployee.getName().isEmpty()) emp.setName(updatedEmployee.getName());
                if (!updatedEmployee.getPhoneNumber().isEmpty()) emp.setPhoneNumber(updatedEmployee.getPhoneNumber());
                if (!updatedEmployee.getEmailAddress().isEmpty()) emp.setEmailAddress(updatedEmployee.getEmailAddress());
                emp.setIndexProject(updatedEmployee.getIndexProject());
                emp.setSalary(updatedEmployee.getSalary());
                emp.setYearOfJoining(updatedEmployee.getYearOfJoining());

                if (emp instanceof Leader && updatedEmployee instanceof Leader) {
                    ((Leader) emp).setGrouptIndex(((Leader) updatedEmployee).getGrouptIndex());
                }
                if (emp instanceof Manager && updatedEmployee instanceof Manager) {
                    ((Manager) emp).setExperienceYear(((Manager) updatedEmployee).getExperienceYear());
                }

                result = true;
                break;
            }
        }

        if (result) {
            // Ghi lại danh sách nhân viên sau khi cập nhật
            List<String> employeesData = new ArrayList<>();
            for (Employee emp : employeeData) {
                employeesData.add(emp.getInfo());
            }
            ReadAndWriteData.writeToFile(employees, employeesData, NOT_APPEND);
        }
        return result;
    }

    public void addProject(Project project) {
        projectService.addProject(project);
    }

    public void removeProject(String filePath) {
        File projectFile = new File(filePath);
        if (!projectFile.exists()) {
            System.err.println("Project file not found: " + filePath);
            return;
        }
        projectService.removeProject(filePath);
    }

    public boolean editProject(File projectFile, Project project) {
        return projectService.editProject(projectFile, project);
    }

    public void showAllProjects() {
        List<Project> projectList = projectService.projectList();

        if (projectList.isEmpty()) {
            System.out.println("No projects found!");
            return;
        }

        System.out.println("List of Projects:");
        for (Project project : projectList) {
            System.out.println("- " + project.getProjectName().replace(".csv", ""));
        }
    }
}
