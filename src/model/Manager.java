package model;

public class Manager extends Employee {
    private int experienceYear;

    public Manager() {
    }

    public Manager(int id, String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee, int salary, int experienceYear) {
        super(id, name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee, salary);
        this.experienceYear = experienceYear;
    }

    public int getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(int experienceYear) {
        this.experienceYear = experienceYear;
    }
}
