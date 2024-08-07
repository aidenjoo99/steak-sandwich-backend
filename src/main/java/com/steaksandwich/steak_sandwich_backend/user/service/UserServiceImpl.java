package com.steaksandwich.steak_sandwich_backend.user.service;

import com.steaksandwich.steak_sandwich_backend.exception.EmailAlreadyInUseException;
import com.steaksandwich.steak_sandwich_backend.exception.UsernameAlreadyExistsException;
import com.steaksandwich.steak_sandwich_backend.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.mail.MessagingException;
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

    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy final PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public UserResponse createUser(UserRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("The username " + request.getUsername() + " already exists");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyInUseException("The email " + request.getEmail() + " is already in use");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        String confirmationUrl = "http://localhost:8080/users/confirm?token=" + user.getConfirmationToken();

        try {
            emailService.sendEmail(
                    user.getEmail(),
                    "Registration Confirmation",
                    "Please confirm your email by clicking the link: " + confirmationUrl
            );
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }

    @Override
    public String confirmUser(String token) {
        if (userRepository.findByConfirmationToken(token).isPresent()) {
            User user = userRepository.findByConfirmationToken(token).get();
            user.setEnabled(true);
            user.setConfirmationToken(null);
            userRepository.save(user);
            return "Email confirmed. You can now log in.";
        } else {
            return "Invalid Token";
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found.");
        }
        User userObj = user.get();

        if (!userObj.isEnabled()) {
            throw new UsernameNotFoundException("User is not enabled.");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .roles(getRoles(userObj))
                .build();
    }

    private String[] getRoles(User user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        } else {
            return user.getRole().split(",");
        }
    }
}
