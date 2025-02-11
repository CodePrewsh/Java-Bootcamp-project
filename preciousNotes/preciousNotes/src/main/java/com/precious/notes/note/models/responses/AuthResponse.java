package com.precious.notes.note.models.responses;

import com.precious.notes.note.models.entities.User;

public class AuthResponse {
    private String token;
    private User user;
    private String message;

    // Constructor with message only
    public AuthResponse(String message) {
        this.message = message;
    }

    // Constructor with message and user
    public AuthResponse(String message, User user) {
        this(message); // Calls the first constructor
        this.user = user;
    }

    // Constructor with message, token, and user
    public AuthResponse(String message, String token, User user) {
        this(message, user); // Calls the second constructor
        this.token = token;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
