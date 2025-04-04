package service.impl;

import model.Orders;
import service.IBooking;
import util.ReadAndWriteData;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class BookingService implements IBooking {
    private static final File orders = new File("E:\\CS M2\\src\\repository\\orders");

    @Override
    public void addBooking(Orders order) {
        List<String> bookingData = new LinkedList<>();
        bookingData.add(order.getInfo());
        ReadAndWriteData.writeToFile(orders,bookingData,true);
    }
}
