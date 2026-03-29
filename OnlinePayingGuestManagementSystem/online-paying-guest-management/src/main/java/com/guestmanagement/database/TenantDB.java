package com.guestmanagement.database;

import com.guestmanagement.model.Tenant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TenantDB {
    
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
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static ArrayList<Tenant> getAll() {
        ArrayList<Tenant> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM tenant";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
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
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }
    
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
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static void delete(int id) {
        Connection conn = Database.connect();
        String sql = "DELETE FROM tenant WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Tenant Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static Tenant searchById(int id) {
        Connection conn = Database.connect();
        String sql = "SELECT * FROM tenant WHERE id=?";
        Tenant t = null;
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                t = new Tenant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return t;
    }
}
