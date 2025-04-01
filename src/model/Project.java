package model;

import java.util.List;

public class Project {
    private int indexID;
    private String projectName;
    private String startDate;
    private String expectedEndDate;
    private Leader leader;
    private Customer customer;
    private List<Employee> employees;
    private String typeOfProject;
    private long expense;
    private long revenue;
    private boolean isPaid = false;
    private String actionHistory;

    public Project(){}

    public Project(int indexID, String projectName, String startDate, String expectedEndDate, Leader leader, Customer customer, List<Employee> employees, String typeOfProject, long expense, long revenue, boolean isPaid, String actionHistory) {
        this.indexID = indexID;
        this.projectName = projectName;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.leader = leader;
        this.customer = customer;
        this.employees = employees;
        this.typeOfProject = typeOfProject;
        this.expense = expense;
        this.revenue = revenue;
        this.isPaid = isPaid;
        this.actionHistory = actionHistory;
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

    public int getIndexID() {
        return indexID;
    }

    public void setIndexID(int indexID) {
        this.indexID = indexID;
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

    public long getExpense() {
        return expense;
    }

    public void setExpense(long expense) {
        this.expense = expense;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(String actionHistory) {
        this.actionHistory = actionHistory;
    }
}
