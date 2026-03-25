package com.guestmanagement.service;

import com.guestmanagement.model;

public class AdminMenu {
	
	static final String ADMIN_EMAIL    = "admin@pg.com";
    static final String ADMIN_PASSWORD = "admin123";
    
    public static boolean login() {
        System.out.println("\n--- Admin Login ---");
        String email = Input.readText("Email    : ");
        String pass  = Input.readText("Password : ");

        if (email.equals(ADMIN_EMAIL) && pass.equals(ADMIN_PASSWORD)) {
            System.out.println("  Welcome, Admin!");
            return true;
        }
        System.out.println("  Wrong email or password.");
        return false;
    }
    
    public static void show() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Property");
            System.out.println("2. Add Room");
            System.out.println("3. View All Properties");
            System.out.println("4. View All Rooms");
            System.out.println("5. View All Bookings");
            System.out.println("6. View All Messages");
            System.out.println("7. Summary Report");
            System.out.println("0. Logout");
            choice = Input.readInt("Enter choice: ");

            if      (choice == 1) addProperty();
            else if (choice == 2) addRoom();
            else if (choice == 3) viewProperties();
            else if (choice == 4) viewRooms();
            else if (choice == 5) viewBookings();
            else if (choice == 6) viewMessages();
            else if (choice == 7) showReport();
            else if (choice == 0) System.out.println("  Logged out.");
            else                  System.out.println("  Invalid choice.");
        }
    }
    
    static void addProperty() {
        System.out.println("\n--- Add Property ---");
        String name     = Input.readText("Name     : ");
        String location = Input.readText("Location : ");
        String owner    = Input.readText("Owner    : ");

        Property p = new Property(Data.nextPropertyId++, name, location, owner);
        Data.properties.add(p);
        System.out.println("  Property added! ID=" + p.id);
    }
    
    static void addRoom() {
        System.out.println("\n--- Add Room ---");

       
        viewProperties();
        if (Data.properties.isEmpty()) {
            System.out.println("  Please add a property first!");
            return;
        }

        int    propId = Input.readInt("Property ID : ");
        String roomNo = Input.readText("Room No     : ");
        String type   = Input.readText("Type (Single/Double/Triple) : ");
        double rent   = Input.readDouble("Rent/month  : ");

        boolean exists = false;
        for (Property p : Data.properties) {
            
        	if (p.id == propId) { 
            	exists = true; 
                break; 
            }
        }
        
        if (!exists) { 
        	System.out.println("  Property not found!"); 
        	return; 
        }

        Room r = new Room(Data.nextRoomId++, propId, roomNo, type, rent);
        Data.rooms.add(r);
        System.out.println("  Room added! ID=" + r.id);
    }

   
    static void viewProperties() {
       
    	if (Data.properties.isEmpty()) {
            System.out.println("  No properties yet.");
            return;
        }
        System.out.println("  --- Properties ---");
        for (Property p : Data.properties) System.out.println("  " + p);
    }
    
    static void viewRooms() {
       
    	if (Data.rooms.isEmpty()) {
            System.out.println("  No rooms yet.");
            return;
        }
        System.out.println("  --- Rooms ---");
        for (Room r : Data.rooms) System.out.println("  " + r);
    }
    
    static void viewBookings() {
        if (Data.bookings.isEmpty()) {
            System.out.println("  No bookings yet.");
            return;
        }
        System.out.println("  --- Bookings ---");
        for (Booking b : Data.bookings) System.out.println("  " + b);
    }
    
    static void viewMessages() {
        if (Data.messages.isEmpty()) {
            System.out.println("  No messages yet.");
            return;
        }
        System.out.println("  --- Messages ---");
        for (Message m : Data.messages) {
            System.out.println("  " + m);
            System.out.println();
        }
    }
    
    
    
    static void showReport() {
     
        int available = 0;
        for (Room r : Data.rooms) {
            if (r.available) available++;
        }

       
        double totalRevenue = 0;
        for (Payment p : Data.payments) {
            totalRevenue += p.amount;
        }

        System.out.println("\n  === SUMMARY REPORT ===");
        System.out.println("  Properties : " + Data.properties.size());
        System.out.println("  Rooms      : " + Data.rooms.size()
                         + "  (Available: " + available + ")");
        System.out.println("  Tenants    : " + Data.tenants.size());
        System.out.println("  Bookings   : " + Data.bookings.size());
        System.out.println("  Revenue    : Rs." + totalRevenue);
        System.out.println("  ======================");
    }
}


}
