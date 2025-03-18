package model;

import java.util.ArrayList;

public class Project {
    private String projectName;
    private String startDate;
    private String expectedEndDate;
    private ArrayList<Person> person;
    private String typeOfProject;
    private long expense;
    private long revenue;
    private boolean isPaid;
    private String actionHistory;

    public Project(){}

    public Project(String projectName, String startDate, String expectedEndDate, ArrayList<Person> person, String typeOfProject, long expense, long revenue, boolean isPaid, String actionHistory) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.person = person;
        this.typeOfProject = typeOfProject;
        this.expense = expense;
        this.revenue = revenue;
        this.isPaid = isPaid;
        this.actionHistory = actionHistory;
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
