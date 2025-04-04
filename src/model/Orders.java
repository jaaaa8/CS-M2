package model;

import util.IDByTimeStampGenerator;

public class Orders {
    private int id;
    private Customer customer;
    private String description;
    private String typeOfOrder;
    private int budget;

    public Orders() {
    }

    public Orders(int id, Customer customer,String description, String typeOfOrder, int budget) {
        this.id = id;
        this.customer = customer;
        this.description = description;
        this.typeOfOrder = typeOfOrder;
        this.budget = budget;
    }

    public Orders(Customer customer, String description, String typeOfOrder, int budget) {
        this.id = IDByTimeStampGenerator.generateId();
        this.customer = customer;
        this.description = description;
        this.typeOfOrder = typeOfOrder;
        this.budget = budget;
    }

    public String getInfo(){
        return id+","+customer.getId()+","+description+","+typeOfOrder+","+budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTypeOfOrder() {
        return typeOfOrder;
    }

    public void setTypeOfOrder(String typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
