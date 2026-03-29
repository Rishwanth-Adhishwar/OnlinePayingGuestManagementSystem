package com.guestmanagement.service;

import com.guestmanagement.model.*;
import com.guestmanagement.database.*;
import java.util.ArrayList;

public class AdminMenu {

    static AdminDB currentAdmin = null;

    public static void show() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- ADMIN PORTAL ---");
            System.out.println("1. Login");
            System.out.println("0. Back");
            choice = Input.readInt("Enter choice: ");

            if (choice == 1) {
                login();
                if (currentAdmin != null)
                    adminMenu();
            } else if (choice == 0) {
                System.out.println("  Going back.");
            } else {
                System.out.println("  Invalid choice.");
            }
        }
    }

    static void login() {
        System.out.println("\n--- Admin Login ---");
        String email = Input.readText("Email    : ");
        String pass = Input.readText("Password : ");

        currentAdmin = AdminDB.login(email, pass);
        if (currentAdmin != null) {
            System.out.println("  Welcome, " + currentAdmin.name + "!");
        } else {
            System.out.println("  Wrong email or password.");
        }
    }

    static void adminMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- ADMIN MENU (" + currentAdmin.name + ") ---");
            System.out.println("1. Manage Properties");
            System.out.println("2. Manage Rooms");
            System.out.println("3. View Tenants");
            System.out.println("4. View Bookings");
            System.out.println("5. View Payments");
            System.out.println("6. View Messages");
            System.out.println("7. View All Data");
            System.out.println("0. Logout");
            choice = Input.readInt("Enter choice: ");

            switch (choice) {
                case 1: manageProperties(); break;
                case 2: manageRooms(); break;
                case 3: viewTenants(); break;
                case 4: viewBookings(); break;
                case 5: viewPayments(); break;
                case 6: viewMessages(); break;
                case 7: viewAllData(); break;
                case 0: System.out.println("  Goodbye, " + currentAdmin.name + "!"); break;
                default: System.out.println("  Invalid choice.");
            }
        }
        currentAdmin = null;
    }

    static void manageProperties() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- MANAGE PROPERTIES ---");
            System.out.println("1. Add Property");
            System.out.println("2. View Properties");
            System.out.println("3. Update Property");
            System.out.println("4. Delete Property");
            System.out.println("0. Back");
            choice = Input.readInt("Enter choice: ");

            if (choice == 1) addProperty();
            else if (choice == 2) viewProperties();
            else if (choice == 3) updateProperty();
            else if (choice == 4) deleteProperty();
            else if (choice != 0) System.out.println("  Invalid choice.");
        }
    }

    static void addProperty() {
        System.out.println("\n--- Add Property ---");
        String name = Input.readText("Name     : ");
        String location = Input.readText("Location : ");
        String owner = Input.readText("Owner    : ");
        
        Property p = new Property(0, name, location, owner);
        PropertyDB.insert(p);
    }

    static void viewProperties() {
        System.out.println("\n--- All Properties ---");
        ArrayList<Property> list = PropertyDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  No properties found.");
        }
        for (Property p : list) {
            System.out.println("  " + p);
        }
    }

    static void updateProperty() {
        viewProperties();
        int id = Input.readInt("Property ID to update : ");
        Property p = PropertyDB.searchById(id);
        
        if (p == null) {
            System.out.println("  Property not found!");
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
    }

    static void deleteProperty() {
        viewProperties();
        int id = Input.readInt("Property ID to delete : ");
        PropertyDB.delete(id);
    }

    static void manageRooms() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- MANAGE ROOMS ---");
            System.out.println("1. Add Room");
            System.out.println("2. View Rooms");
            System.out.println("3. Update Room");
            System.out.println("4. Delete Room");
            System.out.println("0. Back");
            choice = Input.readInt("Enter choice: ");

            if (choice == 1) addRoom();
            else if (choice == 2) viewRooms();
            else if (choice == 3) updateRoom();
            else if (choice == 4) deleteRoom();
            else if (choice != 0) System.out.println("  Invalid choice.");
        }
    }

    static void addRoom() {
        System.out.println("\n--- Add Room ---");
        viewProperties();
        int propertyId = Input.readInt("Property ID : ");
        String roomNo = Input.readText("Room No    : ");
        String type = Input.readText("Type (Single/Double/AC) : ");
        double rent = Input.readDouble("Rent       : ");
        
        Room r = new Room(0, propertyId, roomNo, type, rent);
        RoomDB.insert(r);
    }

    static void viewRooms() {
        System.out.println("\n--- All Rooms ---");
        ArrayList<Room> list = RoomDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  No rooms found.");
        }
        for (Room r : list) {
            System.out.println("  " + r);
        }
    }

    static void updateRoom() {
        viewRooms();
        int id = Input.readInt("Room ID to update : ");
        Room r = RoomDB.searchById(id);
        
        if (r == null) {
            System.out.println("  Room not found!");
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
    }

    static void deleteRoom() {
        viewRooms();
        int id = Input.readInt("Room ID to delete : ");
        RoomDB.delete(id);
    }

    static void viewTenants() {
        System.out.println("\n--- All Tenants ---");
        ArrayList<Tenant> list = TenantDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  No tenants found.");
        }
        for (Tenant t : list) {
            System.out.println("  " + t);
        }
    }

    static void viewBookings() {
        System.out.println("\n--- All Bookings ---");
        ArrayList<Booking> list = BookingDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  No bookings found.");
        }
        for (Booking b : list) {
            System.out.println("  " + b);
        }
    }

    static void viewPayments() {
        System.out.println("\n--- All Payments ---");
        ArrayList<Payment> list = PaymentDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  No payments found.");
        }
        for (Payment p : list) {
            System.out.println("  " + p);
        }
    }

    static void viewMessages() {
        System.out.println("\n--- All Messages ---");
        ArrayList<MessageDB> list = MessageDB.getAll();
        if (list.isEmpty()) {
            System.out.println("  No messages found.");
        }
        for (MessageDB m : list) {
            System.out.println("  " + m);
        }
    }

    static void viewAllData() {
        System.out.println("\n========== ALL DATA ==========");
        viewProperties();
        viewRooms();
        viewTenants();
        viewBookings();
        viewPayments();
        viewMessages();
        System.out.println("==============================");
    }
}
