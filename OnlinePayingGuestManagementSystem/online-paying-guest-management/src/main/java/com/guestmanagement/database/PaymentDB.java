package com.guestmanagement.database;

/**
 * ============================================================
 * Author : Rishwanth,Subathra________________________
 * ============================================================
 *
 * Description :
 * This class is used to manage all payment-related database
 * operations in the Paying Guest Management System.
 * It includes insert, view, update, delete,
 * and search payment operations.
 *
 * ============================================================
 */

import com.guestmanagement.model.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Class
public class PaymentDB {
    
    // Static method / Insert operation
    public static void insert(Payment p) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO payments (booking_id, amount, mode, date) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, p.bookingId);
            pst.setDouble(2, p.amount);
            pst.setString(3, p.mode);
            pst.setString(4, p.date);
            pst.executeUpdate();
            System.out.println("Payment Added!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Fetch all payments
    public static ArrayList<Payment> getAll() {
        ArrayList<Payment> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM payments";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // Object creation
                Payment p = new Payment(
                    rs.getInt("id"),
                    rs.getInt("booking_id"),
                    rs.getDouble("amount"),
                    rs.getString("mode"),
                    rs.getString("date")
                );
                list.add(p);
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
    public static void update(Payment p) {
        Connection conn = Database.connect();
        String sql = "UPDATE payments SET booking_id=?, amount=?, mode=?, date=? WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, p.bookingId);
            pst.setDouble(2, p.amount);
            pst.setString(3, p.mode);
            pst.setString(4, p.date);
            pst.setInt(5, p.id);
            pst.executeUpdate();
            System.out.println("Payment Updated!");
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
        String sql = "DELETE FROM payments WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Payment Deleted!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Search by ID
    public static Payment searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM payments WHERE id=?";
        Payment p = null; // Object reference
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Object creation
                p = new Payment(
                    rs.getInt("id"),
                    rs.getInt("booking_id"),
                    rs.getDouble("amount"),
                    rs.getString("mode"),
                    rs.getString("date")
                );
            }
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return p;
    }
}