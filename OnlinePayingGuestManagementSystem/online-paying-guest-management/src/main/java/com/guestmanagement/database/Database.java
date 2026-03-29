package com.guestmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    public static String HOST = "localhost";
    public static String PORT = "3306";
    public static String DATABASE = "guest_db";
    public static String USER = "root";
    public static String PASSWORD = "jananis@456";
    
    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
            conn = DriverManager.getConnection(url, USER, PASSWORD);
           
        } catch (SQLException e) {
        
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Connection conn = connect();
        disconnect(conn);
    }
}
