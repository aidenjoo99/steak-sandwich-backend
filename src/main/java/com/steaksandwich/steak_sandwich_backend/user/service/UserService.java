package com.steaksandwich.steak_sandwich_backend.user.service;

import org.springframework.stereotype.Service;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserResponse;

@Service
public interface UserService {
    UserResponse createUser(UserRequest request);
}
