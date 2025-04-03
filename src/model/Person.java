package model;

import service.IPerson;

public abstract class Person implements IPerson {
    protected String id;
    protected String name;
    protected String phoneNumber;
    protected String emailAddress;
    protected int indexProject;

    public Person(String name, String phoneNumber, String emailAddress, int indexProject) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.indexProject = indexProject;
    }

    public Person(){}

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getIndexProject() {
        return indexProject;
    }

    public void setIndexProject(int indexProject) {
        this.indexProject = indexProject;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo(){
        return id + "-" + name + "-" + phoneNumber + "-" + emailAddress+"-"+indexProject;
    }
}
