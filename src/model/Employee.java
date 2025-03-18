package model;

public class Employee extends Person {
    private int yearOfJoining;
    private String typeOfEmployee;
    private int salary;

    public Employee(int id, String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee, int salary) {
        super(id, name, phoneNumber, emailAddress, indexProject);
        this.yearOfJoining = yearOfJoining;
        this.typeOfEmployee = typeOfEmployee;
        this.salary = salary;
    }
    public Employee() {}

    public int getSalary() {
        return salary;
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

    public String getInfo(){
        return id+", "+name+", "+phoneNumber+", "+emailAddress+", "+indexProject+", "+typeOfEmployee+", "+salary+", ";
    }

    @Override
    public String toString() {
        return "ID: "+id+"\n" +
                "Name: "+name+"\n" +
                "Phone: "+phoneNumber+"\n" +
                "Email: "+emailAddress+"\n" +
                "Project: "+indexProject+"\n" +
                "Year of Joining: "+yearOfJoining+"\n" +
                "Type of Employee: "+typeOfEmployee+"\n" +
                "Salary: "+salary+"\n";
    }
}
