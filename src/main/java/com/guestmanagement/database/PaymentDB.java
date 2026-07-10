package com.guestmanagement.database;

import com.guestmanagement.exception.PGException;
import com.guestmanagement.model.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDB {

    // Static method - Insert
    public static void insert(Payment p) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "INSERT INTO payments (booking_id, amount, mode, date) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, p.getBookingId());
            pst.setDouble(2, p.getAmount());
            pst.setString(3, p.getMode());
            pst.setString(4, p.getDate());
            pst.executeUpdate();
            System.out.println("Payment Added!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Fetch all
    public static ArrayList<Payment> getAll() {
        ArrayList<Payment> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM payments";
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
        } catch (PGException | SQLException e) {
            System.out.println("  Error fetching payments: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }

    // Static method - Update
    public static void update(Payment p) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "UPDATE payments SET booking_id=?, amount=?, mode=?, date=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, p.getBookingId());
            pst.setDouble(2, p.getAmount());
            pst.setString(3, p.getMode());
            pst.setString(4, p.getDate());
            pst.setInt(5, p.getId());
            pst.executeUpdate();
            System.out.println("Payment Updated!");
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
            String sql = "DELETE FROM payments WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Payment Deleted!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Search by ID
    public static Payment searchById(int id) {
        Connection conn = null;
        Payment p = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM payments WHERE id=?";
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
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return p;
    }
}
