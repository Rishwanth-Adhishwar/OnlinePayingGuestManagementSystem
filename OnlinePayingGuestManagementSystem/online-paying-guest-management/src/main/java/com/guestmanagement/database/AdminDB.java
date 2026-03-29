package com.guestmanagement.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDB {
    
    public int id;
    public String name;
    public String email;
    public String password;
    
    public AdminDB(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public String toString() {
        return "AdminID:" + id + " | " + name + " | " + email;
    }
    
    public static void insert(AdminDB a) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM admin WHERE username=? AND password=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.name);
            pst.setString(2, a.email);
            pst.setString(3, a.password);
            pst.executeUpdate();
            System.out.println("Admin Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static AdminDB login(String email, String password) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM admin WHERE email=? AND password=?";
        AdminDB admin = null;
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            java.sql.ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                admin = new AdminDB(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return admin;
    }
    
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
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static void delete(int id) {
        Connection conn = Database.connect();
        String sql = "DELETE FROM admin WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Admin Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
} 