package org.example.service;

import lombok.SneakyThrows;
import org.example.model.User;
import org.example.repository.JdbcRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private JdbcRepository jdbcRepository;

    public UserService(JdbcRepository jdbcRepository) {
        this.jdbcRepository = jdbcRepository;
    }


    public List<User> getAllUsers() throws SQLException {
        ResultSet resultSet = jdbcRepository.getAllUser();
        List<User> users = mapResultSetToUser(resultSet);
        return users;
    }

    public String updateUser(String id, String firstName, String lastName, String age) throws SQLException {
        ResultSet userById = jdbcRepository.getUserById(id);
        User user = mapResultSetToUser(userById).get(0);
        if (firstName == null) {
            firstName = user.getFirstName();
        }
        if (lastName == null) {
            lastName = user.getLastName();
        }
        if (age == null) {
            age = String.valueOf(user.getAge());
        }
        String affectedId = jdbcRepository.updateUser(id, firstName, lastName, Integer.parseInt(age));
        return affectedId;
    }

    public void createTableIfNotExists() {
        jdbcRepository.createTableIfNotExists();
    }

    public User createUser(String firstName, String lastName, int age) {
        return jdbcRepository.createUser(firstName, lastName, age);
    }

    public String deleteUser(String userId) {
        return jdbcRepository.deleteUser(userId);
    }


    private List<User> mapResultSetToUser(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            String userId = resultSet.getString("user_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            int age = resultSet.getInt("age");
            users.add(new User(userId, firstName, lastName, age));
        }
        return users;
    }
}
