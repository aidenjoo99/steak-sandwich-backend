package com.steaksandwich.steak_sandwich_backend.user.dto;

import com.steaksandwich.steak_sandwich_backend.user.entity.User;

public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private boolean success;
    private String message;


    public UserResponse(User user, boolean success, String message) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.success = success;
        this.message = message;
    }

    public UserResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public UserResponse(User user) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}