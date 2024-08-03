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
}
