package view;

import model.Customer;

public class CustomerView extends PersonView<Customer>{
    @Override
    protected Customer createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        boolean isPaidProject = false;
        int id = 4;
        return new Customer(id,name,phoneNumber,emailAddress,indexProject,isPaidProject);
    }
}
