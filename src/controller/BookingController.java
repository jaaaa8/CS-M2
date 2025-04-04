package controller;

import service.impl.BookingService;

import java.util.Scanner;

public class BookingController {
    public static Scanner sc = new Scanner(System.in);
    private BookingService bookingService = new BookingService();

    public void showBookings() {
        boolean flag = true;
        while (flag) {
            System.out.println("------------ORDERS----------" +
                    "\n1. Tạo order.");

        }
    }
}
