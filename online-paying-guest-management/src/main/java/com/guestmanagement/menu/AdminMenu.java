package com.guestmanagement.menu;

import com.guestmanagement.service.*;

public class AdminMenu {

    public void showMenu() {

        BookingService bookingService = new BookingService();
        ReportService reportService = new ReportService();

        // Sample bookings (so report shows data)
        bookingService.bookRoom(101, 1);
        bookingService.bookRoom(102, 2);

        // Display Report
        reportService.displayBookings(bookingService);

        // Display Dashboard
        reportService.displayDashboard(bookingService);
    }
}