package model;

public class Orders {
    private int id;
    private Customer customer;
    private String description;
    private String typeOfOrder;
    private long budget;

    public Orders() {
    }

    public Orders(int id, Customer customer,String description, String typeOfOrder, long budget) {
        this.id = id;
        this.customer = customer;
        this.description = description;
        this.typeOfOrder = typeOfOrder;
        this.budget = budget;
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

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }
}
