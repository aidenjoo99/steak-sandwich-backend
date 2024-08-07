package com.steaksandwich.steak_sandwich_backend.session.service;

import org.springframework.stereotype.Service;
import com.steaksandwich.steak_sandwich_backend.session.entity.Session;
import com.steaksandwich.steak_sandwich_backend.user.entity.User;

import java.util.Optional;

@Service
public interface SessionService {
  Session createSession(User user);
  Optional<Session> validateSession(String sessionToken);
  void invalidateSession(String sessionToken);
  void invalidateAllSessions(User user);
}
