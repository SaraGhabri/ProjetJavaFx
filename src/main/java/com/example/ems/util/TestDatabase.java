package com.example.ems.util;

public class TestDatabase {
    public static void main(String[] args) {
        // Connect to the database
        DatabaseUtil.connect();

        // Disconnect from the database
        DatabaseUtil.disconnect();
    }
}
