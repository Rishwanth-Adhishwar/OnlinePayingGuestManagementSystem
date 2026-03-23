package com.guestmanagement.service;

import com.guestmanagement.model.Booking;

public class ReportService {

    // Booking Report
    public void displayBookings(BookingService bookingService) {

        Booking[] bookings = bookingService.getAllBookings();
        int count = bookingService.getBookingCount();

        System.out.println("\n---- Booking Report ----");

        for (int i = 0; i < count; i++) {
            System.out.println(bookings[i]);
            System.out.println("----------------------");
        }
    }

    // Dashboard
    public void displayDashboard(BookingService bookingService) {

        RoomService roomService = new RoomService();
        TenantService tenantService = new TenantService();

        System.out.println("\n---- Dashboard Summary ----");
        System.out.println("Total Rooms: " + roomService.countRooms());
        System.out.println("Total Tenants: " + tenantService.countTenants());
        System.out.println("Total Bookings: " + bookingService.getBookingCount());
    }
}