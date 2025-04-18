package service.impl;

import model.Orders;

import java.util.List;

public interface IBooking {
    List<Orders> ordersList();
    void addBooking(Orders order);
    void removeBooking(int id);
    boolean updateBooking(int id);
    void showAllOrder();
}
