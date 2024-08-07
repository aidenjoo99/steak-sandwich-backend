package com.steaksandwich.steak_sandwich_backend.session.entity;

import com.steaksandwich.steak_sandwich_backend.user.entity.User;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "session")
public class Session {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "sessionToken", nullable = false, unique = true)
  private String sessionToken;

  @Column(name = "expiresAt", nullable = false)
  private Instant expiresAt;

  @Column(name = "refreshToken", nullable = false)
  private String refreshToken;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  public Session(String sessionToken, Instant expiresAt, String refreshToken, User user) {
    this.sessionToken = sessionToken;
    this.expiresAt = expiresAt;
    this.refreshToken = refreshToken;
    this.user = user;
  }

  public long getId() {
    return id;
  }

  public String getSessionToken() {
    return sessionToken;
  }

  public Instant getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Instant expiresAt) {
    this.expiresAt = expiresAt;
  }

  public String getRefreshToken() {
    return refreshToken;
  } 

  public User getUser() {
    return user;
  }

}
