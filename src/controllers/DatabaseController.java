package controllers;
import java.sql.*;
import models.User;
import javax.swing.*;

public class DatabaseController {
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

    public boolean createUser(User user) {
        if (connection == null) {
            connect();
        }

        String query = "INSERT INTO users (name, email, cnic, phone, password, isRenting) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCnic());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getPassword());
            statement.setBoolean(6, user.getIsRenting());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error establishing connection!");
            System.err.println("Error while inserting user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public int authenticateUser(String email, String password) {

        if (connection == null) {
            connect();
        }

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);

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

    public User getUserData(int userId) {
        if (connection == null) {
            connect();
        }

        User user = null;
        String query = "SELECT name, cnic, email, phone, isRenting FROM users WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)){

            stmt.setInt(1, userId);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                String name = result.getString("name");
                String cnic = result.getString("cnic");
                String email = result.getString("email");
                String phone = result.getString("phone");
                boolean isRenting = result.getBoolean("isRenting");

                user = new User(name, email, cnic, phone, null, isRenting);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = DatabaseController.connect();
    }
}