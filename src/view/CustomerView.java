package view;

import model.Customer;

public class CustomerView extends PersonView<Customer>{
    @Override
    protected Customer createPerson(String name, String phoneNumber, String emailAddress, int indexProject) {
        System.out.println("Nhập level khách hàng: ");
        int level = sc.nextInt();
        return new Customer(name,phoneNumber,emailAddress,indexProject,level);
    }
}
