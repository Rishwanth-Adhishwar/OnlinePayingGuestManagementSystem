package com.guestmanagement.database;

import com.guestmanagement.exception.PGException;
import com.guestmanagement.model.Displayable;
import com.guestmanagement.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Inheritance - extends Person (is-a relationship)
// Polymorphism - implements Displayable interface
public class AdminDB extends Person implements Displayable {

    // Private fields (Encapsulation) - only admin-specific fields
    private String email;
    private String password;

    // Constructor
    public AdminDB(int id, String name, String email, String password) {
        // Calling parent constructor (Inheritance)
        super(id, name);
        this.email = email;
        this.password = password;
    }

    // Getter and Setter methods
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Implementing abstract method from Person (Polymorphism)
    @Override
    public String getRole() {
        return "Admin";
    }

    // Implementing interface method (Polymorphism)
    @Override
    public void display() {
        printHeader();
        System.out.println("  ID   : " + getId());
        System.out.println("  Name : " + getName());
        System.out.println("  Email: " + email);
    }

    private void printHeader() {
        System.out.println("--- Admin Details ---");
    }

    // Method Overriding
    @Override
    public String toString() {
        return "AdminID:" + getId() + " | " + getName() + " | " + email;
    }

    // ========== Database Operations (Static Methods) ==========

    // Static method - Insert admin
    public static void insert(AdminDB a) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "INSERT INTO admins (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.getName());
            pst.setString(2, a.getEmail());
            pst.setString(3, a.getPassword());
            pst.executeUpdate();
            System.out.println("Admin Added!");
        } catch (PGException | SQLException e) {
            // Multi-catch (Exception Handling feature)
            System.out.println("  Error: " + e.getMessage());
        } finally {
            // Finally block always executes (Exception Handling)
            Database.disconnect(conn);
        }
    }

    // Static method - Login (Abstraction - internal logic hidden)
    public static AdminDB login(String email, String password) {
        Connection conn = null;
        AdminDB admin = null;

        try {
            conn = Database.connect();
            String sql = "SELECT * FROM admins WHERE email=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            java.sql.ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Object creation using constructor
                admin = new AdminDB(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }
        } catch (PGException e) {
            System.out.println("  " + e.getUserMessage());
        } catch (SQLException e) {
            System.out.println("  Error during login: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return admin;
    }

    // Static method - Update
    public static void update(AdminDB a) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "UPDATE admins SET name=?, email=?, password=? WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, a.getName());
            pst.setString(2, a.getEmail());
            pst.setString(3, a.getPassword());
            pst.setInt(4, a.getId());
            pst.executeUpdate();
            System.out.println("Admin Updated!");
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
            String sql = "DELETE FROM admins WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Admin Deleted!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }
}
