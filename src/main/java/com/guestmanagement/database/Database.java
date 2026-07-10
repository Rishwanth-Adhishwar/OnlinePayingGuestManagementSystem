package com.guestmanagement.database;

import com.guestmanagement.exception.PGException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Static variables (Constants)
    public static String HOST = "mysql-313d8acf-guestmanagementsystem-4.j.aivencloud.com";
    public static String PORT = "27913";
    public static String DATABASE = "guest_db";
    public static String USER = "avnadmin";
    public static String PASSWORD = "AVNS_qtP9PXrlBVRWIbjsSIq";

    // Static method - Database connection
    // Uses 'throws' keyword to pass exception to caller (Exception Handling)
    public static Connection connect() throws PGException {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
            conn = DriverManager.getConnection(url, USER, PASSWORD);
        } catch (SQLException e) {
            // Throwing custom exception with original cause (Exception Handling)
            throw new PGException("Database connection failed! Please check your network.", e);
        }
        return conn;
    }

    // Static method - Close connection
    public static void disconnect(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Catching and printing, not throwing since this is cleanup
            System.out.println("  Warning: Could not close database connection.");
        }
    }
}
