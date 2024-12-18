package controllers;

import models.Bicycle;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class BicycleController {

    private static final Connection connection = DatabaseConnector.getConnection();

    //Store new bicycle listing in database
    public boolean insertBicycle(Bicycle bicycle) {
        String query = "INSERT INTO bicycles (name, year, weight, `current_condition`, hourly_rate, location, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, bicycle.getName());
            statement.setInt(2, bicycle.getYear());
            statement.setFloat(3, bicycle.getWeight());
            statement.setString(4, bicycle.getCondition());
            statement.setDouble(5, bicycle.getHourlyRate());
            statement.setString(6, bicycle.getLocation());
            statement.setInt(7, bicycle.getUserId());

            //Execute query
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

    //Get all bicycle listings and return a compiled arraylist
    public ArrayList<Bicycle> getBicycles() {
        ArrayList<Bicycle> bicycleList = new ArrayList<>();
        String query = "SELECT id, name, year, weight, `current_condition`, hourly_rate, location, user_id FROM bicycles";

        try (PreparedStatement statement = connection.prepareStatement(query);

             //Execute query
             ResultSet resultSet = statement.executeQuery()) {

            //Add individual bicycle objects to arraylist
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
        String query = "DELETE FROM bicycles WHERE name = ? AND user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            //place parameter variables in placeholders
            statement.setString(1, bicycle.getName());
            statement.setInt(2, user_id);

            //Execute query
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