package controllers;

import models.User;
import javax.swing.*;
import java.sql.*;

public class UserController {

    private static final Connection connection = DatabaseConnector.getConnection();

    //Store new user in database
    public boolean createUser(User user) {
        String query = "INSERT INTO users (name, email, cnic, phone, password, isRenting) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCnic());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getPassword());
            statement.setBoolean(6, user.getIsRenting());

            //execute query
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error establishing connection!");
            System.err.println("Error while inserting user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //Validate user login credentials from database
    public int authenticateUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);

            //execute query
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int userId = result.getInt("id");
                return userId;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error establishing connection!");
            System.err.println("Error authenticating user: " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    //Get User record by matching user id
    public User getUserData(int userId) {
        User user = null;
        String query = "SELECT name, cnic, email, phone, password, isRenting FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, userId);

            //execute query
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                String name = result.getString("name");
                String cnic = result.getString("cnic");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String password = result.getString("password");
                boolean isRenting = result.getBoolean("isRenting");

                user = new User(name, email, cnic, phone, password, isRenting);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public String getRenterPhone(int userId) {
        String query = "SELECT phone FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String phone = result.getString("phone");
                return phone;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Update user details
    public boolean update(int userId, User user) {
        String query = "UPDATE users SET email = ?, phone = ?, password = ?, isRenting = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.getIsRenting());
            statement.setInt(5, userId);

            //Execute query
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
