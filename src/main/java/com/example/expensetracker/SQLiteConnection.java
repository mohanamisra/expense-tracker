package com.example.expensetracker;

import java.sql.*;

public class SQLiteConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/mohana/IdeaProjects/LoginApp/mydatabase.sqlite");
            return conn;
        }
        catch(Exception e) {
            System.out.println("In sqliteconnection.java");
            System.out.println(e);
//            return null;
        }
        return null;
    }
}