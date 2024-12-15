package com.example.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    // Database URL, Username, and Password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static Connection connection = null;



    // Method to establish a connection
    public static Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Database connected successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    // Method to close the connection
    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to close the database connection: " + e.getMessage());
        }
    }
}