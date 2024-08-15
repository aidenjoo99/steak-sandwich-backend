package com.steaksandwich.steak_sandwich_backend.session.service;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public interface SessionService {
  String createSession(UserDetails user);
  String extractUsername(String jwt);
  boolean validToken(String jwt);
}
