package com.steaksandwich.steak_sandwich_backend.content;

import com.steaksandwich.steak_sandwich_backend.user.dto.UserRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.steaksandwich.steak_sandwich_backend.user.service.UserService;
import com.steaksandwich.steak_sandwich_backend.session.service.SessionService;
import com.steaksandwich.steak_sandwich_backend.exception.UsernameNotFoundException;
import com.steaksandwich.steak_sandwich_backend.session.entity.LoginForm;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContentController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    
    @GetMapping("/home")
    public String handleWelcome() {
        return "home";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome() {
        return "home_admin";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        return "home_user";
    }

    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }

    @GetMapping("/register")
    public String handleRegistration() {
        return "register";
    }

    @GetMapping("confirmation-success")
    public String handleConfirmationSuccess(@RequestParam(value = "continue", required = false) String continueParam) {
        boolean isValid = userService.confirmUser(continueParam);
        return (isValid) ? "confirm" : "fail";
    }
    
    @PostMapping("/authenticate")
    public String authenticateToken(@RequestBody LoginForm user) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return sessionService.createSession(userService.loadUserByUsername(user.getUsername()));
        } else {
            throw new UsernameNotFoundException("Invalid credentials.");
        }
    }
}