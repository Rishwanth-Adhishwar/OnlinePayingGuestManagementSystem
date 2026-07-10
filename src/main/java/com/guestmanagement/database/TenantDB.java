package com.guestmanagement.database;

import com.guestmanagement.exception.PGException;
import com.guestmanagement.model.Tenant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TenantDB {

    // Static method - Insert
    // Using getters instead of direct field access (Encapsulation)
    public static void insert(Tenant t) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "INSERT INTO tenants (name, email, password, phone) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, t.getName());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getPhone());
            pst.executeUpdate();
            System.out.println("Tenant Added!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Fetch all
    public static ArrayList<Tenant> getAll() {
        ArrayList<Tenant> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM tenants";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                // Using constructor with getters later
                Tenant t = new Tenant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("phone")
                );
                list.add(t);
            }
        } catch (PGException | SQLException e) {
            System.out.println("  Error fetching tenants: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }

    // Static method - Update
    public static void update(Tenant t) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "UPDATE tenants SET name=?, email=?, password=?, phone=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, t.getName());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getPhone());
            pst.setInt(5, t.getId());
            pst.executeUpdate();
            System.out.println("Tenant Updated!");
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
            String sql = "DELETE FROM tenants WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Tenant Deleted!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Search by ID
    public static Tenant searchById(int id) {
        Connection conn = null;
        Tenant t = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM tenants WHERE id=?";
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
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return t;
    }
}
