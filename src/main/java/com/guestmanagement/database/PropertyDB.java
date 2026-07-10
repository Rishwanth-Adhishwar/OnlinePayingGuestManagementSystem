package com.guestmanagement.database;

import com.guestmanagement.exception.PGException;
import com.guestmanagement.model.Property;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyDB {

    // Static method - Insert
    public static void insert(Property p) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "INSERT INTO properties (name, location, owner) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.getName());
            pst.setString(2, p.getLocation());
            pst.setString(3, p.getOwner());
            pst.executeUpdate();
            System.out.println("Property Added!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Fetch all
    public static ArrayList<Property> getAll() {
        ArrayList<Property> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM properties";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Property p = new Property(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("owner")
                );
                list.add(p);
            }
        } catch (PGException | SQLException e) {
            System.out.println("  Error fetching properties: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }

    // Static method - Update
    public static void update(Property p) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "UPDATE properties SET name=?, location=?, owner=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.getName());
            pst.setString(2, p.getLocation());
            pst.setString(3, p.getOwner());
            pst.setInt(4, p.getId());
            pst.executeUpdate();
            System.out.println("Property Updated!");
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
            String sql = "DELETE FROM properties WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Property Deleted!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Search by ID
    public static Property searchById(int id) {
        Connection conn = null;
        Property p = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM properties WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                p = new Property(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("owner")
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
