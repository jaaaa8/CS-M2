package model;

import util.RandomIDGenerator;

public class Manager extends Employee {
    private int experienceYear;

    public Manager() {
    }

    public Manager(String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee, int experienceYear) {
        super(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee);
        this.experienceYear = experienceYear;
        this.salary = baseSalary()+baseSalary()*(experienceYear/20);
        this.id = RandomIDGenerator.generateID(this.getClass());
    }

    public Manager(String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee,int salary, String id, int experienceYear) {
        super(name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee);
        this.experienceYear = experienceYear;
        this.salary = salary;
        this.id = id;
    }

    @Override
    public String getInfo(){
        return super.getInfo()+","+experienceYear;
    }

    @Override
    public int baseSalary() {
        return calculateSalary(BASE_M_SALARY);
    }

    public int getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(int experienceYear) {
        this.experienceYear = experienceYear;
    }
}
