package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_team")
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "image_url", length = 2000)
    private String imageUrl;

    @Column(name = "is_active", nullable = false)
    // indica si equipo sigue en el sistema (no ha borrado su cuenta)
    private boolean isActive;

    @Column(name = "is_enabled", nullable = false)
    // indica si un equipo puede jugar y no tiene alguns sancion o suspension
    private boolean isEnabled;

    @OneToMany(mappedBy = "team")
    private List<Card> cards;

    @OneToMany(mappedBy = "team")
    private List<Goal> goals;

    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayMatches;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "team")
    private List<TeamPlayer> teamsPlayer;

    @OneToMany(mappedBy = "team")
    private List<TournamentTeam> tournamentsTeam;

}
