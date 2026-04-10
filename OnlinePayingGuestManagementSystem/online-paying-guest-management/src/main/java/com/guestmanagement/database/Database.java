package com.guestmanagement.database;

/**
 * ============================================================
 * Author : Rishwanth__________________________
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
    public static String HOST = "mysql-313d8acf-guestmanagementsystem-4.j.aivencloud.com";
    public static String PORT = "27913";
    public static String DATABASE = "guest_db";
    public static String USER = "avnadmin";
    public static String PASSWORD = "AVNS_qtP9PXrlBVRWIbjsSIq";
    
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