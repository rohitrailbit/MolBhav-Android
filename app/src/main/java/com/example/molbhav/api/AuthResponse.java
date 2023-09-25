package com.example.molbhav.api;


import com.example.molbhav.entity.User;

public class AuthResponse {
    // Define fields according to your API response JSON
    // For example, if the response contains an access token, you can add a field like:
     private String token;
     private User user;
    // Getters and setters (if needed)


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
