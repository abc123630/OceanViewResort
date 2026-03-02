package com.ocean.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/ocean_view_resort_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";       // replace with your DB username
    private static final String PASSWORD = "Nuwanthi@967"; // replace with your DB password

    // Private constructor to prevent instantiation
    private DBConnection() {}

    // Method to get a database connection
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }

        // Return connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
