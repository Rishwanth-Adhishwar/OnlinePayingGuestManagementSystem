package com.guestmanagement.service;

import com.guestmanagement.model.*;
import com.guestmanagement.database.*;
import java.util.ArrayList;

public class TenantMenu {

    static Tenant currentTenant = null;

    public static void show() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- TENANT PORTAL ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Back");
            choice = Input.readInt("Enter choice: ");

            if (choice == 1)
                register();
            else if (choice == 2) {
                login();
                if (currentTenant != null)
                    tenantMenu();
            } else if (choice == 0)
                System.out.println("  Going back.");
            else
                System.out.println("  Invalid choice.");
        }
    }

    static void register() {
        System.out.println("\n--- Register ---");
        String name = Input.readText("Name     : ");
        String email = Input.readEmail("Email    : ");

        if (getTenantByEmail(email) != null) {
            System.out.println("  Email already registered!");
            return;
        }

        String pass = Input.readText("Password : ");
        String phone = Input.readPhone("Phone (10 digits) : ");

        Tenant t = new Tenant(0, name, email, pass, phone);
        TenantDB.insert(t);
        System.out.println("  Registered successfully!");
    }

    static void login() {
        System.out.println("\n--- Tenant Login ---");
        String email = Input.readEmail("Email    : ");
        String pass = Input.readText("Password : ");

        ArrayList<Tenant> list = TenantDB.getAll();
        for (Tenant t : list) {
            if (t.email.equals(email) && t.password.equals(pass)) {
                currentTenant = t;
                System.out.println("  Welcome, " + t.name + "!");
                return;
            }
        }
        System.out.println("  Wrong email or password.");
        currentTenant = null;
    }

    static Tenant getTenantByEmail(String email) {
        ArrayList<Tenant> list = TenantDB.getAll();
        for (Tenant t : list) {
            if (t.email.equals(email)) {
                return t;
            }
        }
        return null;
    }

    static void tenantMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- TENANT MENU (" + currentTenant.name + ") ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Pay Rent");
            System.out.println("4. Send Message to Admin");
            System.out.println("5. My Bookings");
            System.out.println("0. Logout");
            choice = Input.readInt("Enter choice: ");

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
                System.out.println("  Goodbye, " + currentTenant.name + "!");
            else
                System.out.println("  Invalid choice.");
        }
        currentTenant = null;
    }

    static void viewAvailableRooms() {
        System.out.println("  --- Available Rooms ---");
        ArrayList<Room> list = RoomDB.getAll();
        boolean found = false;
        for (Room r : list) {
            if (r.available) {
                System.out.println("  " + r);
                found = true;
            }
        }
        if (!found)
            System.out.println("  No rooms available right now.");
    }

    static void bookRoom() {
        viewAvailableRooms();

        int roomId = Input.readInt("Room ID to book : ");
        String date = Input.readText("Move-in date    : ");

        Room chosen = RoomDB.searchById(roomId);

        if (chosen == null) {
            System.out.println("  Room not found!");
            return;
        }
        if (!chosen.available) {
            System.out.println("  Room is already booked!");
            return;
        }

        Booking b = new Booking(0, currentTenant.id, roomId, date);
        BookingDB.insert(b);
        
        chosen.available = false;
        RoomDB.update(chosen);

        System.out.println("  Booked! Room:" + chosen.roomNo + " | Rs." + chosen.rent + "/month");
    }

    static void makePayment() {
        myBookings();

        int bookingId = Input.readInt("Booking ID to pay for : ");
        String mode = Input.readText("Payment mode (Cash/UPI/Online) : ");
        String date = Input.readText("Payment date : ");

        ArrayList<Booking> bookings = BookingDB.getAll();
        Booking booking = null;
        for (Booking b : bookings) {
            if (b.id == bookingId && b.tenantId == currentTenant.id) {
                booking = b;
                break;
            }
        }

        if (booking == null) {
            System.out.println("  Booking not found!");
            return;
        }

        Room room = RoomDB.searchById(booking.roomId);
        double rent = (room != null) ? room.rent : 0;

        Payment p = new Payment(0, bookingId, rent, mode, date);
        PaymentDB.insert(p);
        System.out.println("  Payment successful! Rs." + rent + " via " + mode);
    }

    static void sendMessage() {
        String text = Input.readText("Your message : ");
        String date = Input.readText("Date         : ");

        MessageDB m = new MessageDB(0, currentTenant.id, 1, text, date);
        MessageDB.insert(m);
        System.out.println("  Message sent to admin!");
    }

    static void myBookings() {
        System.out.println("  --- My Bookings ---");
        ArrayList<Booking> list = BookingDB.getAll();
        boolean found = false;
        for (Booking b : list) {
            if (b.tenantId == currentTenant.id) {
                System.out.println("  " + b);
                found = true;
            }
        }
        if (!found)
            System.out.println("  You have no bookings yet.");
    }
}
