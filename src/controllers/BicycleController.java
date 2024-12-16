package controllers;

import models.Bicycle;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class BicycleController {
    private static final String URL = "jdbc:mysql://localhost:3306/spinwheels";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    public static Connection connect() {
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

    public boolean insertBicycle(Bicycle bicycle) {
        if (connection == null) {
            connect();
        }

        String query = "INSERT INTO bicycles (name, year, weight, `current_condition`, hourly_rate, location, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, bicycle.getName());
            statement.setInt(2, bicycle.getYear());
            statement.setFloat(3, bicycle.getWeight());
            statement.setString(4, bicycle.getCondition());
            statement.setDouble(5, bicycle.getHourlyRate());
            statement.setString(6, bicycle.getLocation());
            statement.setInt(7, bicycle.getUserId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while inserting data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Bicycle> getBicycles() {
        if (connection == null) {
            connect();
        }

        ArrayList<Bicycle> bicycleList = new ArrayList<>();
        String query = "SELECT id, name, year, weight, `current_condition`, hourly_rate, location, user_id FROM bicycles";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int bicycleId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                float weight = resultSet.getFloat("weight");
                String condition = resultSet.getString("current_condition");
                int hourly_rate = resultSet.getInt("hourly_rate");
                String location = resultSet.getString("location");
                int user_id = resultSet.getInt("user_id");

                Bicycle bicycle = new Bicycle(name, year, weight, condition, hourly_rate, location, user_id);
                bicycleList.add(bicycle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bicycleList;
    }

    public boolean removeBicycle(Bicycle bicycle, int user_id) {
        if (connection == null) {
            connect();
        }

        String query = "DELETE FROM bicycles WHERE name = ? AND user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bicycle.getName());
            statement.setInt(2, user_id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while removing bicycle: ");
            e.printStackTrace();
        }

        return false;
    }

}