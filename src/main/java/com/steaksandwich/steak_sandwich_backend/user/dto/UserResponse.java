package com.steaksandwich.steak_sandwich_backend.user.dto;

import com.steaksandwich.steak_sandwich_backend.user.entity.User;

public class UserResponse {

    private Long id;
    private String username;
    private String password;
    private int points;

    private String role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.points = user.getPoints();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPoints() {
        return points;
    }

    public String getRole() {
        return role;
    }
}