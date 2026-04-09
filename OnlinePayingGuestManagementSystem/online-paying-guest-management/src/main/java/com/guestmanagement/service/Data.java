package com.guestmanagement.service;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class acts as a service layer for retrieving
 * data from different database classes.
 * It provides methods to fetch tenants, properties,
 * rooms, bookings, payments, and messages.
 *
 * OOP Concepts Used :
 * - Class
 * - Methods
 * - Abstraction
 * - Encapsulation
 * - Object Usage
 *
 * ============================================================
 */

import com.guestmanagement.model.*;
import com.guestmanagement.database.*;
import java.util.ArrayList;

// Class
public class Data {
    
    // Static method / fetch all tenants
    public static ArrayList<Tenant> getAllTenants() {
        return TenantDB.getAll();
    }
    
    // Static method / fetch all properties
    public static ArrayList<Property> getAllProperties() {
        return PropertyDB.getAll();
    }
    
    // Static method / fetch all rooms
    public static ArrayList<Room> getAllRooms() {
        return RoomDB.getAll();
    }
    
    // Static method / fetch all bookings
    public static ArrayList<Booking> getAllBookings() {
        return BookingDB.getAll();
    }
    
    // Static method / fetch all payments
    public static ArrayList<Payment> getAllPayments() {
        return PaymentDB.getAll();
    }
    
    // Static method / fetch all messages
    public static ArrayList<MessageDB> getAllMessages() {
        return MessageDB.getAll();
    }
    
    // Static method / search tenant by email
    public static Tenant getTenantByEmail(String email) {
        ArrayList<Tenant> list = TenantDB.getAll();

        // Enhanced for loop
        for (Tenant t : list) {
            if (t.email.equals(email)) {
                return t;
            }
        }
        return null;
    }
    
    // Static method / search room by id
    public static Room getRoomById(int id) {
        return RoomDB.searchById(id);
    }
    
    // Static method / search property by id
    public static Property getPropertyById(int id) {
        return PropertyDB.searchById(id);
    }
}