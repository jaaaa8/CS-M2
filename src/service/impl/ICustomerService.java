package service.impl;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    void pay(Customer customer);
    List<Customer> customerList();
    void addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    void deleteCustomer(String id);
    void showCustomer(String id);
}
