package com.steaksandwich.steak_sandwich_backend.user.controller;

import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserResponse;
import com.steaksandwich.steak_sandwich_backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/register")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/register")
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }
}