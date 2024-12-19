package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_player_account")
public class PlayerAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false, length = 9)
    private String run;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private boolean isActive;

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @OneToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;
}
