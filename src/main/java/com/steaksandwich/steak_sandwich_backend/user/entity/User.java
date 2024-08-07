package com.steaksandwich.steak_sandwich_backend.user.entity;

import com.steaksandwich.steak_sandwich_backend.league.entity.League;
import com.steaksandwich.steak_sandwich_backend.session.entity.Session;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "users")
    private List<League> leagues;

    @OneToMany(mappedBy = "creator")
    private List<League> createdLeagues;

    @Column(name = "points", nullable = false)
    private Integer points;

    @Column(name = "favorite_team_id")
    private Integer favoriteTeamId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Session session;

    public User(String username, String email, String password, String role) {
      this.username = username;
      this.password = password;
      this.email = email;
      this.role = role;
      this.points = 0;
      this.favoriteTeamId = null;
    }

    public User() {}

    public Long getId() {
      return id;
    }

    public String getUsername() {
      return username;
    }

    public String getEmail() {
      return email;
    }

    public String getPassword() {
      return password;
    }

    public String getRole() {
        return role;
    }

    public Integer getPoints() {
      return points;
    }

    public Integer getFavoriteTeamId() {
      return favoriteTeamId;
    }

    public Session getSession() {
      return session;
    }
}
