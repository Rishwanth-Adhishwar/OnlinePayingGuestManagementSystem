package com.guestmanagement.database;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class is used to manage all admin-related database
 * operations in the Paying Guest Management System.
 * It includes insert, login, update, and delete operations.
 *
 * ============================================================
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class
public class AdminDB {
    
    // Instance variables / Encapsulation
    public int id;
    public String name;
    public String email;
    public String password;
    
    // Constructor
    public AdminDB(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    // Method Overriding (toString method from Object class)
    public String toString() {
        return "AdminID:" + id + " | " + name + " | " + email;
    }
    
    // Static method / Abstraction
    public static void insert(AdminDB a) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO admin(name,email,password) VALUES(?,?,?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.name);
            pst.setString(2, a.email);
            pst.setString(3, a.password);
            pst.executeUpdate();
            System.out.println("Admin Added!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    // Static method / Login functionality
    public static AdminDB login(String email, String password) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM admin WHERE email=? AND password=?";
        AdminDB admin = null; // Object reference
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            java.sql.ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                // Object creation
                admin = new AdminDB(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return admin;
    }
    
    // Static method / Update operation
    public static void update(AdminDB a) {
        Connection conn = Database.connect();
        String sql = "UPDATE admin SET name=?, email=?, password=? WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.name);
            pst.setString(2, a.email);
            pst.setString(3, a.password);
            pst.setInt(4, a.id);
            pst.executeUpdate();
            System.out.println("Admin Updated!");
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
        String sql = "DELETE FROM admin WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Admin Deleted!");
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
}