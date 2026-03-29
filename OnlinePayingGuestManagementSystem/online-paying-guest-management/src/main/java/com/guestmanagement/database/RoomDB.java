package com.guestmanagement.database;

import com.guestmanagement.model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDB {
    
    public static void insert(Room r) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO room (property_id, room_no, type, rent, available) VALUES (?, ?, ?, ?, ?)";
        
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
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static ArrayList<Room> getAll() {
        ArrayList<Room> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM room";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                Room r = new Room(
                    rs.getInt("id"),
                    rs.getInt("property_id"),
                    rs.getString("room_no"),
                    rs.getString("type"),
                    rs.getDouble("rent")
                );
                r.available = rs.getBoolean("available");
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }
    
    public static void update(Room r) {
        Connection conn = Database.connect();
        String sql = "UPDATE room SET property_id=?, room_no=?, type=?, rent=?, available=? WHERE id=?";
        
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
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static void delete(int id) {
        Connection conn = Database.connect();
        String sql = "DELETE FROM room WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Room Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static Room searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM room WHERE id=?";
        Room r = null;
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                r = new Room(
                    rs.getInt("id"),
                    rs.getInt("property_id"),
                    rs.getString("room_no"),
                    rs.getString("type"),
                    rs.getDouble("rent")
                );
                r.available = rs.getBoolean("available");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return r;
    }
}
