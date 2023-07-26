package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private int age;

    public User(String firstName, String lastName, int age) {
        userId = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public User(String userId, String firstName, String lastName, int age) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
