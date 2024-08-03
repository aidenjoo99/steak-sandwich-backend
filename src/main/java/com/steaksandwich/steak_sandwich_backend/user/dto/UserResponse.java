package com.steaksandwich.steak_sandwich_backend.user.dto;

import com.steaksandwich.steak_sandwich_backend.user.entity.User;

public class UserResponse {

    private Long id;
    private String username;
    private String password;
    private int points;

    public UserResponse(User user) {
        this.id = user.getId()
    }
}