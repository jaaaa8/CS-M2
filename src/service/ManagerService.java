package service;

import model.Employee;
import model.Leader;
import model.Manager;
import service.impl.IManagerService;
import service.impl.IShowSalary;
import dto.CreateObjectByID;
import util.ReadAndWriteData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ManagerService implements IManagerService, IShowSalary {
    private static final String employeesFilePath = "E:\\CS M2\\src\\repository\\employees.csv";
    private static final boolean APPEND = true;
    private static final boolean NOT_APPEND = false;


    @Override
    public List<Employee> employeeList() {
        List<Employee> employeeData = new ArrayList<>();
        List<String> linesData = ReadAndWriteData.readFile(employeesFilePath);

        for (String line : linesData) {
            String[] partsData = line.split(",");
            if (partsData.length < 8) {
                System.err.println("Invalid data format (too few fields): " + line);
                continue;
            }

            String id = partsData[0];
            String name = partsData[1];
            String phoneNumber = partsData[2];
            String emailAddress = partsData[3];
            int indexProject = Integer.parseInt(partsData[4]);
            String typeOfEmployee = partsData[5];
            int salary = Integer.parseInt(partsData[6]);
            int yearOfJoining = Integer.parseInt(partsData[7]);

            try {
                switch (typeOfEmployee.toLowerCase()) {
                    case "employee":
                        employeeData.add(new Employee(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee, salary, id));
                        break;
                    case "leader":
                        if (partsData.length != 9) {
                            System.err.println("Invalid data format for LEADER: " + line);
                            continue;
                        }
                        int indexGroup = Integer.parseInt(partsData[8]);
                        employeeData.add(new Leader(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee, salary, id, indexGroup));
                        break;
                    case "manager":
                        if (partsData.length != 9) {
                            System.err.println("Invalid data format for MANAGER: " + line);
                            continue;
                        }
                        int experienceYear = Integer.parseInt(partsData[8]);
                        employeeData.add(new Manager(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee, salary, id, experienceYear));
                        break;
                    default:
                        System.err.println("Invalid type of employee: " + typeOfEmployee);
                }
            } catch (Exception e) {
                System.err.println("Error reading employee data: " + line);
            }
        }

        return employeeData;
    }


    @Override
    public void addEmployee(Employee employee) {
        List<String> employeeData = new LinkedList<>();
        employeeData.add(employee.getInfo());
        ReadAndWriteData.writeToFile(employeesFilePath,employeeData,APPEND);
    }

    @Override
    public void deleteEmployee(String id) {
        List<Employee> employeeData = employeeList();
        employeeData.removeIf(emp -> emp.getId().equals(id));
        List<String> employeesData = new ArrayList<>();
        for (Employee emp : employeeData) {
            employeesData.add(emp.getInfo());
        }
        ReadAndWriteData.writeToFile(employeesFilePath,employeesData,NOT_APPEND);
    }

    @Override
    public void demoteEmployee(String id) {

    }

    @Override
    public void promoteEmployee(String id) {

    }

    @Override
    public boolean updateEmployee(String id) {
        boolean result = false;
        Employee employee = CreateObjectByID.getEmployeeByID(id);
        if (employee == null) {
             return false;
        }
        List<Employee> employeeData = employeeList();

        for (Employee emp : employeeData) {
            if (emp.getId().equals(id)) {
                // Cập nhật từng thuộc tính nếu có giá trị mới
                if (!employee.getName().isEmpty()) emp.setName(employee.getName());
                if (!employee.getPhoneNumber().isEmpty()) emp.setPhoneNumber(employee.getPhoneNumber());
                if (!employee.getEmailAddress().isEmpty()) emp.setEmailAddress(employee.getEmailAddress());
                emp.setIndexProject(employee.getIndexProject());
                emp.setSalary(employee.getSalary());
                emp.setYearOfJoining(employee.getYearOfJoining());

                if (emp instanceof Leader && employee instanceof Leader) {
                    ((Leader) emp).setGroupIndex(((Leader) employee).getGroupIndex());
                }
                if (emp instanceof Manager && employee instanceof Manager) {
                    ((Manager) emp).setExperienceYear(((Manager) employee).getExperienceYear());
                }

                result = true;
                break;
            }
        }

        if (result) {
            List<String> employeesData = new ArrayList<>();
            for (Employee emp : employeeData) {
                employeesData.add(emp.getInfo());
            }
            ReadAndWriteData.writeToFile(employeesFilePath, employeesData, NOT_APPEND);
        }
        return result;
    }

    @Override
    public void showSalary(String id) {
        Manager manager = CreateObjectByID.getManagerByID(id);
        if (manager == null) {
            return;
        }
        System.out.println("Luong cua ban la: "+manager.baseSalary());
    }
}
