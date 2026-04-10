package com.guestmanagement.database;

/**
 * ============================================================
 * Author : Rishwanth__________________________
 * ============================================================
 *
 * Description :
 * This class is used to manage all booking-related database
 * operations in the Paying Guest Management System.
 * It includes insert, view, update, delete, and search operations.
 *
 * ============================================================
 */

import com.guestmanagement.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Class
public class BookingDB {
    
    // Static method / Insert operation
    public static void insert(Booking b) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO bookings (tenant_id, room_id, date) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, b.tenantId);
            pst.setInt(2, b.roomId);
            pst.setString(3, b.date);
            pst.executeUpdate();
            System.out.println("Booking Added!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Fetch all records
    public static ArrayList<Booking> getAll() {
        ArrayList<Booking> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM bookings";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // Object creation
                Booking b = new Booking(
                    rs.getInt("id"),
                    rs.getInt("tenant_id"),
                    rs.getInt("room_id"),
                    rs.getString("date")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }
    
    // Static method / Update operation
    public static void update(Booking b) {
        Connection conn = Database.connect();
        String sql = "UPDATE bookings SET tenant_id=?, room_id=?, date=? WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, b.tenantId);
            pst.setInt(2, b.roomId);
            pst.setString(3, b.date);
            pst.setInt(4, b.id);
            pst.executeUpdate();
            System.out.println("Booking Updated!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Delete operation
    public static void delete(int id) {
        Connection conn = Database.connect();
        String sql = "DELETE FROM bookings WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Booking Deleted!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Search operation
    public static Booking searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM bookings WHERE id=?";
        Booking b = null; // Object reference
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Object creation
                b = new Booking(
                    rs.getInt("id"),
                    rs.getInt("tenant_id"),
                    rs.getInt("room_id"),
                    rs.getString("date")
                );
            }
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return b;
    }
}