package com.guestmanagement.database;

import com.guestmanagement.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDB {
    
    public static void insert(Booking b) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO booking (tenant_id, room_id, date) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, b.tenantId);
            pst.setInt(2, b.roomId);
            pst.setString(3, b.date);
            pst.executeUpdate();
            System.out.println("Booking Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static ArrayList<Booking> getAll() {
        ArrayList<Booking> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM booking";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Booking b = new Booking(
                    rs.getInt("id"),
                    rs.getInt("tenant_id"),
                    rs.getInt("room_id"),
                    rs.getString("date")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }
    
    public static void update(Booking b) {
        Connection conn = Database.connect();
        String sql = "UPDATE booking SET tenant_id=?, room_id=?, date=? WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, b.tenantId);
            pst.setInt(2, b.roomId);
            pst.setString(3, b.date);
            pst.setInt(4, b.id);
            pst.executeUpdate();
            System.out.println("Booking Updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static void delete(int id) {
        Connection conn = Database.connect();
        String sql = "DELETE FROM booking WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Booking Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static Booking searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM booking WHERE id=?";
        Booking b = null;
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                b = new Booking(
                    rs.getInt("id"),
                    rs.getInt("tenant_id"),
                    rs.getInt("room_id"),
                    rs.getString("date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return b;
    }
}
