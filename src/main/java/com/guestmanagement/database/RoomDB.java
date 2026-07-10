package com.guestmanagement.database;

import com.guestmanagement.exception.PGException;
import com.guestmanagement.model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDB {

    // Static method - Insert
    public static void insert(Room r) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "INSERT INTO rooms (property_id, room_no, type, rent, available) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, r.getPropertyId());
            pst.setString(2, r.getRoomNo());
            pst.setString(3, r.getType());
            pst.setDouble(4, r.getRent());
            pst.setBoolean(5, r.isAvailable());
            pst.executeUpdate();
            System.out.println("Room Added!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Fetch all
    public static ArrayList<Room> getAll() {
        ArrayList<Room> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM rooms";
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
                r.setAvailable(rs.getBoolean("available"));
                list.add(r);
            }
        } catch (PGException | SQLException e) {
            System.out.println("  Error fetching rooms: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }

    // Static method - Update
    public static void update(Room r) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "UPDATE rooms SET property_id=?, room_no=?, type=?, rent=?, available=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, r.getPropertyId());
            pst.setString(2, r.getRoomNo());
            pst.setString(3, r.getType());
            pst.setDouble(4, r.getRent());
            pst.setBoolean(5, r.isAvailable());
            pst.setInt(6, r.getId());
            pst.executeUpdate();
            System.out.println("Room Updated!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Delete
    public static void delete(int id) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "DELETE FROM rooms WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Room Deleted!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Search by ID
    public static Room searchById(int id) {
        Connection conn = null;
        Room r = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM rooms WHERE id=?";
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
                r.setAvailable(rs.getBoolean("available"));
            }
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return r;
    }
}
