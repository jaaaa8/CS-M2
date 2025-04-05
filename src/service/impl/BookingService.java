package service.impl;

import model.Customer;
import model.Orders;
import service.IBooking;
import util.CreateObjectByID;
import util.ReadAndWriteData;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookingService implements IBooking {
    private static final File orders = new File("E:\\CS M2\\src\\repository\\orders");
    private static final boolean APPEND = true;
    private static final boolean NOT_APPEND = false;

    @Override
    public List<Orders> ordersList() {
        List<Orders> ordersList = new ArrayList<>();
        List<String> linesData = ReadAndWriteData.readFile(orders);

        for (String line : linesData) {
            String[] partsData = line.split(",");
            if (partsData.length < 5) {
                System.err.println("Data format exception at line: " + line);
                continue;
            }

            try {
                int id = Integer.parseInt(partsData[0]);
                String customerId = partsData[1];
                String description = partsData[2];
                String typeOfOrder = partsData[3];
                int budget = Integer.parseInt(partsData[4]);

                Customer customer = CreateObjectByID.getCustomerByID(customerId);
                if (customer == null) {
                    System.err.println("Customer not found for ID: " + customerId);
                    continue;
                }

                ordersList.add(new Orders(id, customer, description, typeOfOrder, budget));
            } catch (Exception e) {
                System.err.println("Error reading order data: " + e.getMessage());
            }
        }

        return ordersList;
    }


    @Override
    public void addBooking(Orders order) {
        List<String> bookingData = new LinkedList<>();
        bookingData.add(order.getInfo());
        ReadAndWriteData.writeToFile(orders,bookingData,APPEND);
    }

    @Override
    public void removeBooking(int id) {
        List<Orders> ordersList = ordersList();
        ordersList.removeIf(or->or.getId() == id );
        List<String> ordersData = new ArrayList<>();
        for (Orders order : ordersList) {
            ordersData.add(order.getInfo());
        }
        ReadAndWriteData.writeToFile(orders,ordersData,NOT_APPEND);
    }

    @Override
    public boolean updateBooking(int id) {
        boolean result = false;

        Orders newOrder = CreateObjectByID.getOrdersByID(id);
        if (newOrder == null) {
            return false;
        }

        List<Orders> ordersData = ordersList();

        for (Orders order : ordersData) {
            if (order.getId() == id) {
                // Cập nhật từng trường nếu hợp lệ
                if (newOrder.getCustomer() != null) order.setCustomer(newOrder.getCustomer());
                if (!newOrder.getDescription().isEmpty()) order.setDescription(newOrder.getDescription());
                if (!newOrder.getTypeOfOrder().isEmpty()) order.setTypeOfOrder(newOrder.getTypeOfOrder());
                order.setBudget(newOrder.getBudget());

                result = true;
                break;
            }
        }

        if (result) {
            List<String> ordersLines = new ArrayList<>();
            for (Orders order : ordersData) {
                ordersLines.add(order.getInfo());
            }
            ReadAndWriteData.writeToFile(orders, ordersLines, NOT_APPEND);
        }

        return result;
    }

    @Override
    public void showAllOrder() {
        List<Orders> allOrders = ordersList();

        if (allOrders.isEmpty()) {
            System.out.println("Hiện không có đơn hàng nào.");
        } else {
            System.out.println("Danh sách tất cả đơn hàng:");
            for (Orders order : allOrders) {
                System.out.println(order);
            }
        }
    }

}
