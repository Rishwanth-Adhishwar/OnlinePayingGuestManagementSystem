package com.guestmanagement.database;

/**
 * ============================================================
 * Author : __________________________
 * ============================================================
 *
 * Description :
 * This class is used to establish and close the database
 * connection for the Paying Guest Management System.
 * It contains methods for connecting and disconnecting
 * from the MySQL database.
 *
 * ============================================================
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class
public class Database {
    
    // Static variables / Constants for DB configuration
    public static String HOST = "#";
    public static String PORT = "#";
    public static String DATABASE = "#";
    public static String USER = "#";
    public static String PASSWORD = "#";
    
    // Static method / Database connection
    public static Connection connect() {
        Connection conn = null;
        try {
            // URL creation
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
            
            // Establish connection
            conn = DriverManager.getConnection(url, USER, PASSWORD);
           
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        }
        return conn;
    }
    
    // Static method / Close connection
    public static void disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            // Exception Handling
            e.printStackTrace();
        }
    }
    
    // Main method / Testing connection
    public static void main(String[] args) {
        Connection conn = connect();
        disconnect(conn);
    }
}