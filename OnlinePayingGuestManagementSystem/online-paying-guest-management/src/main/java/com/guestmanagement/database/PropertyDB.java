package com.guestmanagement.database;

/**
 * ============================================================
 * Author : _Rishwanth_________________________
 * ============================================================
 *
 * Description :
 * This class is used to manage all property-related database
 * operations in the Paying Guest Management System.
 * It includes insert, view, update, delete,
 * and search property operations.
 *
 * ============================================================
 */

import com.guestmanagement.model.Property;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Class
public class PropertyDB {
    
    // Static method / Insert operation
    public static void insert(Property p) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO properties (name, location, owner) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.name);
            pst.setString(2, p.location);
            pst.setString(3, p.owner);
            pst.executeUpdate();
            System.out.println("Property Added!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Fetch all properties
    public static ArrayList<Property> getAll() {
        ArrayList<Property> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM properties";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // Object creation
                Property p = new Property(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("owner")
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
    public static void update(Property p) {
        Connection conn = Database.connect();
        String sql = "UPDATE properties SET name=?, location=?, owner=? WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.name);
            pst.setString(2, p.location);
            pst.setString(3, p.owner);
            pst.setInt(4, p.id);
            pst.executeUpdate();
            System.out.println("Property Updated!");
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
        String sql = "DELETE FROM properties WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Property Deleted!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Search by ID
    public static Property searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM properties WHERE id=?";
        Property p = null; // Object reference
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Object creation
                p = new Property(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getString("owner")
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