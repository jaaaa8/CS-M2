package model;

import util.RandomIDGenerator;

public class Leader extends Employee {
    private int grouptIndex;

    public Leader(String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee, int groupIndex) {
        super(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee);
        this.grouptIndex = groupIndex;
        this.salary = baseSalary();
        this.id = RandomIDGenerator.generateID(this.getClass());
    }

    public Leader(String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee, int salary, String id, int groupIndex) {
        super(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee);
        this.grouptIndex = groupIndex;
        this.salary = salary;
        this.id = id;
    }

    public Leader() {}

    @Override
    public int baseSalary() {
        return calculateSalary(BASE_L_SALARY);
    }

    public int getGrouptIndex() {
        return grouptIndex;
    }

    public void setGrouptIndex(int grouptIndex) {
        this.grouptIndex = grouptIndex;
    }

    @Override
    public String getInfo(){
        return super.getInfo()+"-"+ grouptIndex;
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
                "Group: "+ grouptIndex +"\n";
    }
}
