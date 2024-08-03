package com.steaksandwich.steak_sandwich_backend.user.entity;

import com.steaksandwich.steak_sandwich_backend.league.entity.League;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<League> leagues;

    @OneToMany(mappedBy = "creator")
    private List<League> createdLeagues;

    @Column(nullable = false)
    private Integer points;

    @Column(name = "favorite_team_id")
    private Integer favoriteTeamId;

    public User(String username, String email, String password, Integer favoriteTeamId) {
      this.username = username;
      this.password = password;
      this.email = email;
      this.points = 0;
      this.favoriteTeamId = null;
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

    public Integer getPoints() {
      return points;
    }

    public Integer getFavoriteTeamId() {
      return favoriteTeamId;
    }
}
