package com.steaksandwich.steak_sandwich_backend.user.service;

import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
}
