package service;

import model.Customer;

import java.util.List;

public interface ICustomerService {
    void pay(Customer customer);
    List<Customer> customerList();
    void addCustomer(Customer customer);
    boolean updateCustomer(String id);
    void deleteCustomer(Customer customer);
}
