package com.guestmanagement.service;

import com.guestmanagement.model.*;
import com.guestmanagement.database.*;
import java.util.ArrayList;

public class Data {
    
    public static ArrayList<Tenant> getAllTenants() {
        return TenantDB.getAll();
    }
    
    public static ArrayList<Property> getAllProperties() {
        return PropertyDB.getAll();
    }
    
    public static ArrayList<Room> getAllRooms() {
        return RoomDB.getAll();
    }
    
    public static ArrayList<Booking> getAllBookings() {
        return BookingDB.getAll();
    }
    
    public static ArrayList<Payment> getAllPayments() {
        return PaymentDB.getAll();
    }
    
    public static ArrayList<MessageDB> getAllMessages() {
        return MessageDB.getAll();
    }
    
    public static Tenant getTenantByEmail(String email) {
        ArrayList<Tenant> list = TenantDB.getAll();
        for (Tenant t : list) {
            if (t.email.equals(email)) {
                return t;
            }
        }
        return null;
    }
    
    public static Room getRoomById(int id) {
        return RoomDB.searchById(id);
    }
    
    public static Property getPropertyById(int id) {
        return PropertyDB.searchById(id);
    }
}
