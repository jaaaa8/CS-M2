package model;

public class Leader extends Employee {
    private int groupIndex;

    public Leader(int id, String name, String phoneNumber, String emailAddress, int indexProject, int yearOfJoining, String typeOfEmployee, int salary, int groupIndex) {
        super(id, name, phoneNumber, emailAddress, indexProject, yearOfJoining, typeOfEmployee, salary);
        this.groupIndex = groupIndex;
    }

    public Leader() {}

    public int getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }

    @Override
    public String toString() {
        return "ID: "+id+"\n" +
                "Name: "+name+"\n" +
                "Phone: "+phoneNumber+"\n" +
                "Email: "+emailAddress+"\n" +
                "Project: "+indexProject+"\n" +
                "Year of Joining: "+getYearOfJoining()+"\n" +
                "Type of Employee: "+getTypeOfEmployee()+"\n" +
                "Salary: "+getSalary()+"\n" +
                "Group: "+groupIndex+"\n";
    }
}
