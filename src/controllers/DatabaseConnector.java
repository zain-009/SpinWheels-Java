package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    //Database configuration constants
    private static final String URL = "jdbc:mysql://localhost:3306/spinwheels";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    //Establish connection with database
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to the database!");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Failed to connect to the database!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
