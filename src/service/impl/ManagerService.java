package service.impl;

import model.Employee;
import model.Leader;
import model.Manager;
import service.IManagerService;
import util.ReadAndWriteData;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ManagerService implements IManagerService {
    private static final File employees = new File("E:\\CS M2\\src\\repository\\employees.csv");
    private static final boolean APPEND = true;
    private static final boolean NOT_APPEND = false;

    @Override
    public List<Employee> employeeList() {
        List<Employee> employeeData = new ArrayList<>();
        List<String> linesDate = ReadAndWriteData.readFile(employees);

        for (String line : linesDate) {
            String[] partsData = line.split("-");
            if (partsData.length < 8) continue;
            String id = partsData[0];
            String name = partsData[1];
            String phoneNumber = partsData[2];
            String emailAddress = partsData[3];
            String typeOfEmployee = partsData[4];
            int indexProject = Integer.parseInt(partsData[5]);
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
        List<String> employees = new LinkedList<>();
        employees.add(employee.getInfo());
    }

    @Override
    public void deleteEmployee(Employee employee) {
        List<String> employees = new LinkedList<>();
        employees.remove(employee.getInfo());
    }

    @Override
    public void demoteEmployee(Employee employee) {

    }

    @Override
    public void promoteEmployee(Employee employee) {

    }

    @Override
    public void updateEmployee(Employee employee) {

    }
}
