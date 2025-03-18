package model;

public class Customer extends Person {
    private boolean isPaidProject = false;

    public Customer() {
    }

    public Customer(int id,String name, String phoneNumber, String emailAddress, int indexProject, boolean isPaidProject) {
        super(id,name, phoneNumber, emailAddress, indexProject);
        this.isPaidProject = isPaidProject;
    }

    public boolean isPaidProject() {
        return isPaidProject;
    }

    public void setPaidProject(boolean paidProject) {
        isPaidProject = paidProject;
    }

    @Override
    public String toString() {
        return "ID: "+id+"\n" +
                "Name: "+name+"\n" +
                "Phone: "+phoneNumber+"\n" +
                "Email: "+emailAddress+"\n" +
                "Project: "+indexProject+"\n" +
                "PaidProject: "+((isPaidProject)? "Đã thanh toán.":"Chưa thanh toán." )+"\n";
    }
}
