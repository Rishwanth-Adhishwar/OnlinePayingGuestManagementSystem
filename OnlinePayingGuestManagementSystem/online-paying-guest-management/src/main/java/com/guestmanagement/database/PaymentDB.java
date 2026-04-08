package com.guestmanagement.database;

import com.guestmanagement.model.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDB {
    
    public static void insert(Payment p) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO payment (booking_id, amount, mode, date) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, p.bookingId);
            pst.setDouble(2, p.amount);
            pst.setString(3, p.mode);
            pst.setString(4, p.date);
            pst.executeUpdate();
            System.out.println("Payment Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static ArrayList<Payment> getAll() {
        ArrayList<Payment> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM payment";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
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
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }
    
    public static void update(Payment p) {
        Connection conn = Database.connect();
        String sql = "UPDATE payment SET booking_id=?, amount=?, mode=?, date=? WHERE id=?";
        
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
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static void delete(int id) {
        Connection conn = Database.connect();
        String sql = "DELETE FROM payment WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Payment Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static Payment searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM payment WHERE id=?";
        Payment p = null;
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                p = new Payment(
                    rs.getInt("id"),
                    rs.getInt("booking_id"),
                    rs.getDouble("amount"),
                    rs.getString("mode"),
                    rs.getString("date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return p;
    }
}
