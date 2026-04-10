package com.guestmanagement.database;

/**
 * ============================================================
 * Author : Subathra________________________
 * ============================================================
 *
 * Description :
 * This class is used to manage all room-related database
 * operations in the Paying Guest Management System.
 * It includes insert, view, update, delete,
 * and search room operations.
 *
 * ============================================================
 */

import com.guestmanagement.model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Class
public class RoomDB {
    
    // Static method / Insert operation
    public static void insert(Room r) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO rooms (property_id, room_no, type, rent, available) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, r.propertyId);
            pst.setString(2, r.roomNo);
            pst.setString(3, r.type);
            pst.setDouble(4, r.rent);
            pst.setBoolean(5, r.available);
            pst.executeUpdate();
            System.out.println("Room Added!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Fetch all rooms
    public static ArrayList<Room> getAll() {
        ArrayList<Room> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM rooms";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // Object creation
                Room r = new Room(
                    rs.getInt("id"),
                    rs.getInt("property_id"),
                    rs.getString("room_no"),
                    rs.getString("type"),
                    rs.getDouble("rent")
                );
                
                // Setting additional property
                r.available = rs.getBoolean("available");
                list.add(r);
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
    public static void update(Room r) {
        Connection conn = Database.connect();
        String sql = "UPDATE rooms SET property_id=?, room_no=?, type=?, rent=?, available=? WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, r.propertyId);
            pst.setString(2, r.roomNo);
            pst.setString(3, r.type);
            pst.setDouble(4, r.rent);
            pst.setBoolean(5, r.available);
            pst.setInt(6, r.id);
            pst.executeUpdate();
            System.out.println("Room Updated!");
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
        String sql = "DELETE FROM rooms WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Room Deleted!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Search by ID
    public static Room searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM rooms WHERE id=?";
        Room r = null; // Object reference
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Object creation
                r = new Room(
                    rs.getInt("id"),
                    rs.getInt("property_id"),
                    rs.getString("room_no"),
                    rs.getString("type"),
                    rs.getDouble("rent")
                );
                
                // Setting additional property
                r.available = rs.getBoolean("available");
            }
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return r;
    }
}