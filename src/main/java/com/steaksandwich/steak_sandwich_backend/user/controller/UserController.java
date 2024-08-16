package com.steaksandwich.steak_sandwich_backend.user.controller;

import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserResponse;
import com.steaksandwich.steak_sandwich_backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/aideng")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    // For Thymeleaf form submissions
    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmUser(@RequestParam String token) {
        boolean success = userService.confirmUser(token);
        // Return redirection URL based on success
        String redirectUrl = success ? "/confirmation-success" : "/confirmation-failure";
        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, redirectUrl).build();
    }
}