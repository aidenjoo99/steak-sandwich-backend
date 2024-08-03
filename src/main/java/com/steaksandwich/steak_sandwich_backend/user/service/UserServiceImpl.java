package com.steaksandwich.steak_sandwich_backend.user.service;

import org.springframework.stereotype.Service;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserResponse;
import com.steaksandwich.steak_sandwich_backend.user.entity.User;

@Service
public class UserServiceImpl implements UserService {
  UserResponse createUser(UserRequest request) {
    User user = new User(request.getUsername(), request.getEmail(), 
      request.getPassword());
    return new UserResponse(user);
  }
}
