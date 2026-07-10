package com.guestmanagement.database;

import com.guestmanagement.exception.PGException;
import com.guestmanagement.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDB {

    // Static method - Insert
    public static void insert(Booking b) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "INSERT INTO bookings (tenant_id, room_id, date) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, b.getTenantId());
            pst.setInt(2, b.getRoomId());
            pst.setString(3, b.getDate());
            pst.executeUpdate();
            System.out.println("Booking Added!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Fetch all
    public static ArrayList<Booking> getAll() {
        ArrayList<Booking> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM bookings";
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
        } catch (PGException | SQLException e) {
            System.out.println("  Error fetching bookings: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }

    // Static method - Update
    public static void update(Booking b) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "UPDATE bookings SET tenant_id=?, room_id=?, date=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, b.getTenantId());
            pst.setInt(2, b.getRoomId());
            pst.setString(3, b.getDate());
            pst.setInt(4, b.getId());
            pst.executeUpdate();
            System.out.println("Booking Updated!");
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
            String sql = "DELETE FROM bookings WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Booking Deleted!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Search by ID
    public static Booking searchById(int id) {
        Connection conn = null;
        Booking b = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM bookings WHERE id=?";
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
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return b;
    }
}
