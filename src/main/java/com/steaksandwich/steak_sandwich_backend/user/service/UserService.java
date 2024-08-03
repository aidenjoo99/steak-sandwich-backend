package com.steaksandwich.steak_sandwich_backend.user.service;

import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserResponse;
import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    UserResponse createUser(UserRequest request);
}
