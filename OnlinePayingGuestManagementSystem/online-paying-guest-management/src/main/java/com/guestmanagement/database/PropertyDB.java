package com.guestmanagement.database;

import com.guestmanagement.model.Property;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PropertyDB {
    
    public static void insert(Property p) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO property (name, location, owner) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.name);
            pst.setString(2, p.location);
            pst.setString(3, p.owner);
            pst.executeUpdate();
            System.out.println("Property Added!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static ArrayList<Property> getAll() {
        ArrayList<Property> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM property";
        
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }
    
    public static void update(Property p) {
        Connection conn = Database.connect();
        String sql = "UPDATE property SET name=?, location=?, owner=? WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.name);
            pst.setString(2, p.location);
            pst.setString(3, p.owner);
            pst.setInt(4, p.id);
            pst.executeUpdate();
            System.out.println("Property Updated!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static void delete(int id) {
        Connection conn = Database.connect();
        String sql = "DELETE FROM property WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Property Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static Property searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM property WHERE id=?";
        Property p = null;
        
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return p;
    }
}
