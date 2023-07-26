package org.example.repository;


import org.example.config.JdbcUtil;
import org.example.model.User;

import java.sql.*;
import java.util.List;

public class JdbcRepository {

    public User createUser(String firstName, String lastName, int age) {
        User user = new User(firstName, lastName, age);
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO \"custom_user\" (user_id, first_name, last_name, age)" +
                    "VALUES(?, ?, ?, ?)");
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public void createTableIfNotExists() {
        Connection connection = JdbcUtil.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS \"custom_user\" ("
                    + "user_id VARCHAR(255) PRIMARY KEY,"
                    + "first_name VARCHAR(50) NOT NULL,"
                    + "last_name VARCHAR(50) NOT NULL,"
                    + "age INT NOT NULL)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet getAllUser() {
        Connection connection = JdbcUtil.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            return statement.executeQuery("SELECT * FROM \"custom_user\";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String deleteUser(String id) {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM \"custom_user\" WHERE user_id = ?"
            );
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


    public String updateUser(String id, String firstName, String lastName, int age) {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "UPDATE \"custom_user\"" +
                            "SET first_name = ?, last_name = ?, age = ?" +
                            "WHERE user_id = ?;"
            );
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, id);
            int affectedRows = preparedStatement.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet getUserById(String id) {
        Connection connection = JdbcUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"custom_user\" WHERE user_id = ?;"
            );
            preparedStatement.setString(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
