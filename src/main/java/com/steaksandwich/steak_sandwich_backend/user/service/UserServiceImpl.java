package com.steaksandwich.steak_sandwich_backend.user.service;

import com.steaksandwich.steak_sandwich_backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserResponse;
import com.steaksandwich.steak_sandwich_backend.user.entity.User;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public UserResponse createUser(UserRequest request) {
    User user = new User(request.getUsername(), request.getEmail(), request.getPassword());

    User savedUser = userRepository.save(user);
    return new UserResponse(savedUser);
  }
}
