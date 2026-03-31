package com.guestmanagement.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageDB {
    
    public int id;
    public int senderId;
    public int receiverId;
    public String message;
    public String date;
    
    public MessageDB(int id, int senderId, int receiverId, String message, String date) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.date = date;
    }
    
    public String toString() {
        return "MsgID:" + id + " | From:" + senderId + " | To:" + receiverId 
             + " | " + message + " | " + date;
    }
    
    public static void insert(MessageDB m) {
        Connection conn = Database.connect();
        String sql = "INSERT INTO message (sender_id, receiver_id, message, date) VALUES (?, ?, ?, ?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, m.senderId);
            pst.setInt(2, m.receiverId);
            pst.setString(3, m.message);
            pst.setString(4, m.date);
            pst.executeUpdate();
            System.out.println("Message Sent!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static ArrayList<MessageDB> getAll() {
        ArrayList<MessageDB> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM message";
        
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }
    
    public static void delete(int id) {
        Connection conn = Database.connect();
        String sql = "DELETE FROM message WHERE id=?";
        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Message Deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
    }
    
    public static ArrayList<MessageDB> getByReceiver(int receiverId) {
        ArrayList<MessageDB> list = new ArrayList<>();
        Connection conn = Database.connect();
        String sql = "SELECT * FROM message WHERE receiver_id=?";
        
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Database.disconnect(conn);
        }
        return list;
    }
}
