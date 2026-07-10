package com.guestmanagement.database;

import com.guestmanagement.exception.PGException;
import com.guestmanagement.model.Displayable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Demonstrates Encapsulation, Polymorphism (implements Displayable)
public class MessageDB implements Displayable {

    // Private fields (Encapsulation)
    private int id;
    private int senderId;
    private int receiverId;
    private String message;
    private String date;

    // Constructor
    public MessageDB(int id, int senderId, int receiverId, String message, String date) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.date = date;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Polymorphism - implementing Displayable
    @Override
    public void display() {
        printHeader();
        System.out.println("  Msg ID  : " + id);
        System.out.println("  From    : " + senderId);
        System.out.println("  To      : " + receiverId);
        System.out.println("  Message : " + message);
        System.out.println("  Date    : " + date);
    }

    private void printHeader() {
        System.out.println("--- MessageDB Details ---");
    }

    // Method Overriding
    @Override
    public String toString() {
        return "MsgID:" + id + " | From:" + senderId + " | To:" + receiverId
             + " | " + message + " | " + date;
    }

    // ========== Database Operations ==========

    // Static method - Insert
    public static void insert(MessageDB m) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "INSERT INTO messages (sender_id, receiver_id, message, date) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, m.getSenderId());
            pst.setInt(2, m.getReceiverId());
            pst.setString(3, m.getMessage());
            pst.setString(4, m.getDate());
            pst.executeUpdate();
            System.out.println("Message Sent!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Fetch all
    public static ArrayList<MessageDB> getAll() {
        ArrayList<MessageDB> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM messages";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                MessageDB m = new MessageDB(
                    rs.getInt("id"),
                    rs.getInt("sender_id"),
                    rs.getInt("receiver_id"),
                    rs.getString("message"),
                    rs.getString("date")
                );
                list.add(m);
            }
        } catch (PGException | SQLException e) {
            System.out.println("  Error fetching messages: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }

    // Static method - Delete
    public static void delete(int id) {
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "DELETE FROM messages WHERE id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Message Deleted!");
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
    }

    // Static method - Fetch by receiver
    public static ArrayList<MessageDB> getByReceiver(int receiverId) {
        ArrayList<MessageDB> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Database.connect();
            String sql = "SELECT * FROM messages WHERE receiver_id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, receiverId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                MessageDB m = new MessageDB(
                    rs.getInt("id"),
                    rs.getInt("sender_id"),
                    rs.getInt("receiver_id"),
                    rs.getString("message"),
                    rs.getString("date")
                );
                list.add(m);
            }
        } catch (PGException | SQLException e) {
            System.out.println("  Error: " + e.getMessage());
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }
}
