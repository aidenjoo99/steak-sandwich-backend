package com.steaksandwich.steak_sandwich_backend.session.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.steaksandwich.steak_sandwich_backend.session.repository.SessionRepository;
import com.steaksandwich.steak_sandwich_backend.session.entity.Session;
import com.steaksandwich.steak_sandwich_backend.user.entity.User;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {
  
  private SessionRepository sessionRepository;

  private static final int SESSION_TOKEN_LENGTH = 64;

  @Autowired
  public SessionServiceImpl(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  public Session createSession(User user) {
    String sessionToken = UUID.randomUUID().toString();
    String refreshToken = UUID.randomUUID().toString();
    Instant expiresAt = Instant.now().plus(SESSION_TOKEN_LENGTH, ChronoUnit.HOURS);
    return sessionRepository.save(new Session(sessionToken, expiresAt, refreshToken, user));
  }
  
  public Optional<Session> validateSession(String sessionToken) {
    Optional<Session> sessionOpt = sessionRepository.findBySessionToken(sessionToken);
    if (sessionOpt.isPresent() && sessionOpt.get().getExpiresAt().isAfter(Instant.now())) {
      Session session = sessionOpt.get();
      session.setExpiresAt(Instant.now().plus(SESSION_TOKEN_LENGTH, ChronoUnit.HOURS));
      sessionRepository.save(session);
      return Optional.of(session);
    }
    return Optional.empty();
  }

  public void invalidateSession(String sessionToken) {
    sessionRepository.findBySessionToken(sessionToken).ifPresent(sessionRepository::delete);
  }

  public void invalidateAllSessions(User user) {
    sessionRepository.deleteByUserId(user.getId());
  }
}
