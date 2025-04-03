package model;

import util.RandomIDGenerator;

public class Customer extends Person {
    private int level;

    public Customer() {
    }

    public Customer(String name, String phoneNumber, String emailAddress, int indexProject, int level) {
        super(name, phoneNumber, emailAddress, indexProject);
        this.level = level;
        this.id = RandomIDGenerator.generateID(this.getClass());
    }

    public Customer(String name, String phoneNumber, String emailAddress, int indexProject, int level, String id) {
        super(name, phoneNumber, emailAddress, indexProject);
        this.level = level;
        this.id = id;
    }

    @Override
    public String getInfo(){
        return super.getInfo()+"-"+level;
    }

    @Override
    public String toString() {
        return  "ID: "+id+"\n" +
                "Name: "+name+"\n" +
                "Phone: "+phoneNumber+"\n" +
                "Email: "+emailAddress+"\n" +
                "Project: "+indexProject+"\n" +
                "Level: " +level+"\n";
    }
}
