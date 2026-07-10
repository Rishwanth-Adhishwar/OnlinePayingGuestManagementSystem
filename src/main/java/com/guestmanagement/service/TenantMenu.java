package com.guestmanagement.service;

import com.guestmanagement.exception.PGException;
import com.guestmanagement.model.*;
import com.guestmanagement.database.*;
import java.util.ArrayList;

// Class demonstrating:
// 1. Encapsulation (using getters/setters)
// 2. Exception Handling (try-catch-finally, custom exception)
// 3. Polymorphism (using interface reference)
// 4. Object creation

public class TenantMenu {

    // Static variable - stores currently logged-in tenant
    private static Tenant currentTenant = null;

    // Static method - tenant portal
    public static void show() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n\uD83D\uDC64 \u2502\u2502\u2502 TENANT PORTAL \u2502\u2502\u2502");
            System.out.println("\u2795 1. Register");
            System.out.println("\uD83D\uDD10 2. Login");
            System.out.println("\uD83D\uDEAA 0. Back");
            choice = Input.readInt("Enter choice: ", 0, 2);

            if (choice == 1)
                register();
            else if (choice == 2) {
                login();
                if (currentTenant != null)
                    tenantMenu();
            } else if (choice == 0)
                System.out.println("  \uD83D\uDEAA Going back.");
        }
    }

    // Static method - tenant registration
    static void register() {
        try {
            System.out.println("\n\u2795 Tenant Registration");
            String name = Input.readText("Name     : ");
            String email = Input.readEmail("Email    : ");

            // Using getter (Encapsulation)
            if (getTenantByEmail(email) != null) {
                System.out.println("  \u26A0 Email already registered!");
                return;
            }

            String pass = Input.readText("Password : ");
            String phone = Input.readPhone("Phone (10 digits) : ");

            Tenant t = new Tenant(0, name, email, pass, phone);
            TenantDB.insert(t);
            System.out.println("  \u2705 Registered successfully!");
        } catch (Exception e) {
            System.out.println("  Registration failed: " + e.getMessage());
        }
    }

    // Static method - tenant login
    static void login() {
        System.out.println("\n\uD83D\uDD10 Tenant Login");
        String email = Input.readEmail("Email    : ");
        String pass = Input.readText("Password : ");

        // Demonstrating custom exception usage (Exception Handling)
        try {
            Input.validateEmail(email);
        } catch (PGException e) {
            System.out.println("  " + e.getUserMessage());
            currentTenant = null;
            return;
        }

        ArrayList<Tenant> list = TenantDB.getAll();

        for (Tenant t : list) {
            // Using getters (Encapsulation)
            if (t.getEmail().equals(email) && t.getPassword().equals(pass)) {
                currentTenant = t;
                System.out.println("  \u2705 Welcome, " + t.getName() + "!");
                return;
            }
        }

        System.out.println("  \u274C Wrong email or password.");
        currentTenant = null;
    }

    // Static method - search tenant by email
    static Tenant getTenantByEmail(String email) {
        ArrayList<Tenant> list = TenantDB.getAll();

        for (Tenant t : list) {
            if (t.getEmail().equals(email)) {
                return t;
            }
        }

        return null;
    }

    // Static method - tenant main menu
    static void tenantMenu() {
        int choice = -1;
        while (choice != 0) {
            // Using getter
            System.out.println("\n\uD83C\uDFE0 \u2502\u2502\u2502 TENANT MENU (" + currentTenant.getName() + ") \u2502\u2502\u2502");
            System.out.println("\uD83D\uDC41 1. View Available Rooms");
            System.out.println("\uD83D\uDCC5 2. Book a Room");
            System.out.println("\uD83D\uDCB8 3. Pay Rent");
            System.out.println("\uD83D\uDCE7 4. Send Message to Admin");
            System.out.println("\uD83D\uDCCB 5. My Bookings");
            System.out.println("\uD83D\uDEAA 0. Logout");
            choice = Input.readInt("Enter choice: ", 0, 5);

            if (choice == 1)
                viewAvailableRooms();
            else if (choice == 2)
                bookRoom();
            else if (choice == 3)
                makePayment();
            else if (choice == 4)
                sendMessage();
            else if (choice == 5)
                myBookings();
            else if (choice == 0)
                System.out.println("  \uD83D\uDE4B Goodbye, " + currentTenant.getName() + "!");
        }

        currentTenant = null;
    }

    // Static method - view available rooms
    static void viewAvailableRooms() {
        System.out.println("\n\uD83D\uDC41 Available Rooms");
        ArrayList<Room> list = RoomDB.getAll();
        boolean found = false;

        for (Room r : list) {
            // Using isAvailable getter for boolean (Encapsulation)
            if (r.isAvailable()) {
                System.out.println("  " + r);
                found = true;
            }
        }

        if (!found)
            System.out.println("  \uD83D\uDCC2 No rooms available right now.");
    }

    // Static method - book room
    static void bookRoom() {
        viewAvailableRooms();

        int roomId = Input.readInt("Room ID to book : ");
        String date = Input.readText("Move-in date    : ");

        Room chosen = RoomDB.searchById(roomId);

        if (chosen == null) {
            System.out.println("  \u26A0 Room not found!");
            return;
        }

        if (!chosen.isAvailable()) {
            System.out.println("  \u274C Room is already booked!");
            return;
        }

        // Using getter for currentTenant's id (Encapsulation)
        Booking b = new Booking(0, currentTenant.getId(), roomId, date);
        BookingDB.insert(b);

        // Using setter
        chosen.setAvailable(false);
        RoomDB.update(chosen);

        // Using getters
        System.out.println("  \u2705 Booked! Room: " + chosen.getRoomNo() + " | Rs." + chosen.getRent() + "/month");
    }

    // Static method - make payment
    static void makePayment() {
        myBookings();

        int bookingId = Input.readInt("Booking ID to pay for : ");
        String mode = Input.readText("Payment mode (Cash/UPI/Online) : ");
        String date = Input.readText("Payment date : ");

        ArrayList<Booking> bookings = BookingDB.getAll();
        Booking booking = null;

        for (Booking b : bookings) {
            // Using getters
            if (b.getId() == bookingId && b.getTenantId() == currentTenant.getId()) {
                booking = b;
                break;
            }
        }

        if (booking == null) {
            System.out.println("  \u26A0 Booking not found!");
            return;
        }

        Room room = RoomDB.searchById(booking.getRoomId());
        double rent = (room != null) ? room.getRent() : 0;

        Payment p = new Payment(0, bookingId, rent, mode, date);
        PaymentDB.insert(p);

        System.out.println("  \u2705 Payment successful! Rs." + rent + " via " + mode);
    }

    // Static method - send message to admin
    static void sendMessage() {
        String text = Input.readText("Your message : ");
        String date = Input.readText("Date         : ");

        MessageDB m = new MessageDB(0, currentTenant.getId(), 1, text, date);
        MessageDB.insert(m);

        System.out.println("  \u2705 Message sent to admin!");
    }

    // Static method - view tenant bookings
    static void myBookings() {
        System.out.println("\n\uD83D\uDCCB My Bookings");
        ArrayList<Booking> list = BookingDB.getAll();
        boolean found = false;

        for (Booking b : list) {
            // Using getter
            if (b.getTenantId() == currentTenant.getId()) {
                System.out.println("  " + b);
                found = true;
            }
        }

        if (!found)
            System.out.println("  \uD83D\uDCC5 You have no bookings yet.");
    }
}
