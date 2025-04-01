package model;

import util.RandomIDGenerator;

public class Leader extends Employee {
    private int projectIndex;

    public Leader(String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee, int groupIndex) {
        super(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee);
        this.projectIndex = groupIndex;
        this.salary = baseSalary();
        this.id = RandomIDGenerator.generateID(this.getClass());
    }

    public Leader(String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee, int salary, String id, int groupIndex) {
        super(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee);
        this.projectIndex = groupIndex;
        this.salary = salary;
        this.id = id;
    }

    public Leader() {}

    @Override
    public int baseSalary() {
        return calculateSalary(BASE_L_SALARY);
    }

    public int getProjectIndex() {
        return projectIndex;
    }

    public void setProjectIndex(int projectIndex) {
        this.projectIndex = projectIndex;
    }

    @Override
    public String getInfo(){
        return super.getInfo()+"-"+ projectIndex;
    }

    @Override
    public String toString() {
        return  "ID: "+id+"\n" +
                "Name: "+name+"\n" +
                "Phone: "+phoneNumber+"\n" +
                "Email: "+emailAddress+"\n" +
                "Project: "+indexProject+"\n" +
                "Year of Joining: "+getYearOfJoining()+"\n" +
                "Type of Employee: "+getTypeOfEmployee()+"\n" +
                "Salary: "+getSalary()+"\n" +
                "Group: "+ projectIndex +"\n";
    }
}
