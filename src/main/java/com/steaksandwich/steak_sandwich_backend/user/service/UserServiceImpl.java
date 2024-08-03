package com.steaksandwich.steak_sandwich_backend.user.service;

import com.steaksandwich.steak_sandwich_backend.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserResponse;
import com.steaksandwich.steak_sandwich_backend.user.entity.User;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  } 

  public List<UserResponse> getUsers() {
    return userRepository.findAll().stream()
                .map(user -> new UserResponse(user))
                .collect(Collectors.toList());
  }

  public UserResponse createUser(UserRequest request) {
    User user = new User(request.getUsername(), request.getEmail(), request.getPassword());

    User savedUser = userRepository.save(user);
    return new UserResponse(savedUser);
  }
}
