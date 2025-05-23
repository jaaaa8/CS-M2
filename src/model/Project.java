package model;

import java.util.List;

public class Project {
    private int idProject;
    private String projectName;
    private String startDate;
    private String expectedEndDate;
    private Leader leader;
    private Customer customer;
    private List<Employee> employees;
    private String typeOfProject;
    private boolean isPaid = false;

    public Project(){}

    public Project(int idProject, String projectName, String startDate, String expectedEndDate, Leader leader, Customer customer, List<Employee> employees, String typeOfProject, boolean isPaid) {
        this.idProject = idProject;
        this.projectName = projectName;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.leader = leader;
        this.customer = customer;
        this.employees = employees;
        this.typeOfProject = typeOfProject;
        this.isPaid = isPaid;
    }

    public Project(String projectName, String startDate, String expectedEndDate, Leader leader, Customer customer, List<Employee> employees, String typeOfProject, boolean isPaid) {
        this.idProject = 0;
        this.projectName = projectName;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.leader = leader;
        this.customer = customer;
        this.employees = employees;
        this.typeOfProject = typeOfProject;
        this.isPaid = isPaid;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(String expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public String getTypeOfProject() {
        return typeOfProject;
    }

    public void setTypeOfProject(String typeOfProject) {
        this.typeOfProject = typeOfProject;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
