package com.guestmanagement.database;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class is used to manage all tenant-related database
 * operations in the Paying Guest Management System.
 * It includes insert, view, update, delete,
 * and search tenant operations.
 *
 * ============================================================
 */

import com.guestmanagement.model.Tenant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Class
public class TenantDB {
    
    // Static method / Insert operation
    public static void insert(Tenant t) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO tenant (name, email, password, phone) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, t.name);
            pst.setString(2, t.email);
            pst.setString(3, t.password);
            pst.setString(4, t.phone);
            pst.executeUpdate();
            System.out.println("Tenant Added!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Fetch all tenants
    public static ArrayList<Tenant> getAll() {
        ArrayList<Tenant> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM tenant";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // Object creation
                Tenant t = new Tenant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone")
                );
                list.add(t);
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
    public static void update(Tenant t) {
        Connection conn = Database.connect();
        String sql = "UPDATE tenant SET name=?, email=?, password=?, phone=? WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, t.name);
            pst.setString(2, t.email);
            pst.setString(3, t.password);
            pst.setString(4, t.phone);
            pst.setInt(5, t.id);
            pst.executeUpdate();
            System.out.println("Tenant Updated!");
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
        String sql = "DELETE FROM tenant WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Tenant Deleted!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Search by ID
    public static Tenant searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM tenant WHERE id=?";
        Tenant t = null; // Object reference
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Object creation
                t = new Tenant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return t;
    }
}