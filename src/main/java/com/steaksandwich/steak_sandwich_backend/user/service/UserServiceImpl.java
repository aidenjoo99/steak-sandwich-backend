package com.steaksandwich.steak_sandwich_backend.user.service;

import com.steaksandwich.steak_sandwich_backend.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import com.steaksandwich.steak_sandwich_backend.user.dto.UserResponse;
import com.steaksandwich.steak_sandwich_backend.user.entity.User;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(@Lazy final UserRepository userRepository, @Lazy final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user))
                .collect(Collectors.toList());
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(User user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        } else {
            return user.getRole().split(",");
        }
    }
}
