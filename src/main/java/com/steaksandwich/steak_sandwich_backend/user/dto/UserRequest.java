package com.steaksandwich.steak_sandwich_backend.user.dto;

public class UserRequest {

    private String username;
    private String email;
    private String password;

    private String role;

    public UserRequest() {}

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}