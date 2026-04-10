package com.guestmanagement.service;

/**
 * ============================================================
 * Author : Rishwanth,Subathra__________________________
 * ============================================================
 *
 * Description :
 * This class handles all admin-side operations in the
 * Paying Guest Management System.
 * Includes login, property management, room management,
 * and viewing tenants, bookings, payments, messages,
 * and all system data.
 *
 * ============================================================
 */

import com.guestmanagement.model.*;
import com.guestmanagement.database.*;
import java.util.ArrayList;

// Class
public class AdminMenu {

    // Static variable / stores current logged-in admin
    static AdminDB currentAdmin = null;

    // Static method / shows admin portal
    public static void show() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n\uD83D\uDCBB \u2502\u2502\u2502 ADMIN PORTAL \u2502\u2502\u2502");
            System.out.println("\uD83D\uDC64 1. Login");
            System.out.println("\uD83D\uDEAA 0. Back");
            choice = Input.readInt("Enter choice: ");

            if (choice == 1) {
                login();
                if (currentAdmin != null)
                    adminMenu();
            } else if (choice == 0) {
                System.out.println("  \uD83D\uDEAA Going back.");
            } else {
                System.out.println("  \u26A0 Invalid choice.");
            }
        }
    }

    // Static method / admin login
    static void login() {
        System.out.println("\n\uD83D\uDD10 Admin Login");
        String email = Input.readText("Email    : ");
        String pass = Input.readText("Password : ");

        currentAdmin = AdminDB.login(email, pass);
        if (currentAdmin != null) {
            System.out.println("  \u2705 Welcome, " + currentAdmin.name + "!");
        } else {
            System.out.println("  \u274C Wrong email or password.");
        }
    }

    // Static method / admin main menu
    static void adminMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n\uD83C\uDFE6 \u2502\u2502\u2502 ADMIN MENU (" + currentAdmin.name + ") \u2502\u2502\u2502");
            System.out.println("\uD83C\uDFE0 1. Manage Properties");
            System.out.println("\uD83D\uDCB0 2. Manage Rooms");
            System.out.println("\uD83D\uDC64 3. View Tenants");
            System.out.println("\uD83D\uDCC5 4. View Bookings");
            System.out.println("\uD83D\uDCB8 5. View Payments");
            System.out.println("\uD83D\uDCE7 6. View Messages");
            System.out.println("\uD83D\uDCCB 7. View All Data");
            System.out.println("\uD83D\uDEAA 0. Logout");
            choice = Input.readInt("Enter choice: ");

            switch (choice) {
                case 1: manageProperties(); break;
                case 2: manageRooms(); break;
                case 3: viewTenants(); break;
                case 4: viewBookings(); break;
                case 5: viewPayments(); break;
                case 6: viewMessages(); break;
                case 7: viewAllData(); break;
                case 0: System.out.println("  \uD83D\uDE4B Goodbye, " + currentAdmin.name + "!"); break;
                default: System.out.println("  \u26A0 Invalid choice.");
            }
        }
        currentAdmin = null;
    }

    // Static method / property management
    static void manageProperties() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n\uD83C\uDFE0 MANAGE PROPERTIES");
            System.out.println("\u2795 1. Add Property");
            System.out.println("\uD83D\uDC41 2. View Properties");
            System.out.println("\u270F 3. Update Property");
            System.out.println("\uD83D\uDDD1 4. Delete Property");
            System.out.println("\uD83D\uDEAA 0. Back");
            choice = Input.readInt("Enter choice: ");

            if (choice == 1) addProperty();
            else if (choice == 2) viewProperties();
            else if (choice == 3) updateProperty();
            else if (choice == 4) deleteProperty();
            else if (choice != 0) System.out.println("  \u26A0 Invalid choice.");
        }
    }

    // Static method / add property
    static void addProperty() {
        System.out.println("\n\u2795 Add Property");
        String name = Input.readText("Name     : ");
        String location = Input.readText("Location : ");
        String owner = Input.readText("Owner    : ");

        // Object creation
        Property p = new Property(0, name, location, owner);
        PropertyDB.insert(p);
        System.out.println("  \u2705 Property added successfully!");
    }

    // Static method / view properties
    static void viewProperties() {
        System.out.println("\n\uD83D\uDC41 All Properties");
        ArrayList<Property> list = PropertyDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  \uD83D\uDCC1 No properties found.");
        }
        for (Property p : list) {
            System.out.println("  " + p);
        }
    }

    // Static method / update property
    static void updateProperty() {
        viewProperties();
        int id = Input.readInt("Property ID to update : ");
        Property p = PropertyDB.searchById(id);

        if (p == null) {
            System.out.println("  \u26A0 Property not found!");
            return;
        }

        System.out.println("Enter new details (press Enter to keep old value):");
        String name = Input.readText("Name     : ");
        String location = Input.readText("Location : ");
        String owner = Input.readText("Owner    : ");

        if (!name.isEmpty()) p.name = name;
        if (!location.isEmpty()) p.location = location;
        if (!owner.isEmpty()) p.owner = owner;

        PropertyDB.update(p);
        System.out.println("  \u2705 Property updated successfully!");
    }

    // Static method / delete property
    static void deleteProperty() {
        viewProperties();
        int id = Input.readInt("Property ID to delete : ");
        PropertyDB.delete(id);
        System.out.println("  \uD83D\uDDD1 Property deleted!");
    }

    // Static method / room management
    static void manageRooms() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n\uD83D\uDCB0 MANAGE ROOMS");
            System.out.println("\u2795 1. Add Room");
            System.out.println("\uD83D\uDC41 2. View Rooms");
            System.out.println("\u270F 3. Update Room");
            System.out.println("\uD83D\uDDD1 4. Delete Room");
            System.out.println("\uD83D\uDEAA 0. Back");
            choice = Input.readInt("Enter choice: ");

            if (choice == 1) addRoom();
            else if (choice == 2) viewRooms();
            else if (choice == 3) updateRoom();
            else if (choice == 4) deleteRoom();
            else if (choice != 0) System.out.println("  \u26A0 Invalid choice.");
        }
    }

    // Static method / add room
    static void addRoom() {
        System.out.println("\n\u2795 Add Room");
        viewProperties();
        int propertyId = Input.readInt("Property ID : ");
        String roomNo = Input.readText("Room No    : ");
        String type = Input.readText("Type (Single/Double/AC) : ");
        double rent = Input.readDouble("Rent       : ");

        // Object creation
        Room r = new Room(0, propertyId, roomNo, type, rent);
        RoomDB.insert(r);
        System.out.println("  \u2705 Room added successfully!");
    }

    // Static method / view rooms
    static void viewRooms() {
        System.out.println("\n\uD83D\uDC41 All Rooms");
        ArrayList<Room> list = RoomDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  \uD83D\uDCC2 No rooms found.");
        }
        for (Room r : list) {
            System.out.println("  " + r);
        }
    }

    // Static method / update room
    static void updateRoom() {
        viewRooms();
        int id = Input.readInt("Room ID to update : ");
        Room r = RoomDB.searchById(id);

        if (r == null) {
            System.out.println("  \u26A0 Room not found!");
            return;
        }

        System.out.println("Enter new details:");
        String roomNo = Input.readText("Room No    : ");
        String type = Input.readText("Type       : ");
        String rentStr = Input.readText("Rent       : ");
        String availStr = Input.readText("Available (true/false) : ");

        if (!roomNo.isEmpty()) r.roomNo = roomNo;
        if (!type.isEmpty()) r.type = type;
        if (!rentStr.isEmpty()) r.rent = Double.parseDouble(rentStr);
        if (!availStr.isEmpty()) r.available = Boolean.parseBoolean(availStr);

        RoomDB.update(r);
        System.out.println("  \u2705 Room updated successfully!");
    }

    // Static method / delete room
    static void deleteRoom() {
        viewRooms();
        int id = Input.readInt("Room ID to delete : ");
        RoomDB.delete(id);
        System.out.println("  \uD83D\uDDD1 Room deleted!");
    }

    // Static method / view tenants
    static void viewTenants() {
        System.out.println("\n\uD83D\uDC64 All Tenants");
        ArrayList<Tenant> list = TenantDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  \uD83D\uDCDD No tenants found.");
        }
        for (Tenant t : list) {
            System.out.println("  " + t);
        }
    }

    // Static method / view bookings
    static void viewBookings() {
        System.out.println("\n\uD83D\uDCC5 All Bookings");
        ArrayList<Booking> list = BookingDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  \uD83D\uDCC5 No bookings found.");
        }
        for (Booking b : list) {
            System.out.println("  " + b);
        }
    }

    // Static method / view payments
    static void viewPayments() {
        System.out.println("\n\uD83D\uDCB8 All Payments");
        ArrayList<Payment> list = PaymentDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  \uD83D\uDCB8 No payments found.");
        }
        for (Payment p : list) {
            System.out.println("  " + p);
        }
    }

    // Static method / view messages
    static void viewMessages() {
        System.out.println("\n\uD83D\uDCE7 All Messages");
        ArrayList<MessageDB> list = MessageDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  \uD83D\uDCE7 No messages found.");
        }
        for (MessageDB m : list) {
            System.out.println("  " + m);
        }
    }

    // Static method / view complete data
    static void viewAllData() {
        System.out.println("\n\u2B50========== ALL DATA ==========\u2B50");
        viewProperties();
        viewRooms();
        viewTenants();
        viewBookings();
        viewPayments();
        viewMessages();
        System.out.println("\u2B50==============================\u2B50");
    }
}