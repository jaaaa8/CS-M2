package model;

import util.RandomIDGenerator;

import java.time.LocalDate;

public class Employee extends Person {
    protected int yearOfJoining;
    protected String typeOfEmployee;
    protected int salary;

    public Employee(String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee) {
        super(name, phoneNumber, emailAddress, indexProject);
        this.yearOfJoining = yearOfJoining;
        this.typeOfEmployee = typeOfEmployee;
        this.salary = baseSalary();
        this.id = RandomIDGenerator.generateID(this.getClass());
    }

    public Employee(String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee, int salary, String id) {
        super(name, phoneNumber, emailAddress, indexProject);
        this.yearOfJoining = yearOfJoining;
        this.typeOfEmployee = typeOfEmployee;
        this.salary = salary;
        this.id = id;
    }

    public Employee() {}

    public int baseSalary() {
        return calculateSalary(BASE_E_SALARY);
    }

    protected int calculateSalary(int baseSalary) {
        int thisYear = LocalDate.now().getYear();
        if (thisYear - yearOfJoining >= 1 && thisYear - yearOfJoining < 5) {
            return baseSalary + (thisYear - yearOfJoining) * 500;
        } else if (thisYear - yearOfJoining >= 5) {
            return baseSalary + 3000;
        } else {
            return baseSalary;
        }
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getTypeOfEmployee() {
        return typeOfEmployee;
    }

    public void setTypeOfEmployee(String typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }

    public int getYearOfJoining() {
        return yearOfJoining;
    }

    public void setYearOfJoining(int yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    @Override
    public String getInfo(){
        return super.getInfo()+"-"+typeOfEmployee+"-"+salary+"-"+yearOfJoining;
    }

    @Override
    public String toString() {
        return  "ID: "+id+"\n" +
                "Name: "+name+"\n" +
                "Phone: "+phoneNumber+"\n" +
                "Email: "+emailAddress+"\n" +
                "Project: "+indexProject+"\n" +
                "Year of Joining: "+yearOfJoining+"\n" +
                "Type of Employee: "+typeOfEmployee+"\n" +
                "Salary: "+salary+"\n";
    }
}
