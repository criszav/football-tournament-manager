package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_tournament")
public class Tournament {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "number_of_teams", nullable = false)
    private int numberOfTeams;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "tournament")
    private List<Card> cards;

    @OneToMany(mappedBy = "tournament")
    private List<Match> matches;

    @OneToMany(mappedBy = "tournament")
    private List<TeamPlayer> teamsPlayer;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "tournament_status_id", nullable = false)
    private Long tournamentStatusId;

    @ManyToOne
    @JoinColumn(name = "tournament_status_id", insertable = false, updatable = false)
    private TournamentStatus tournamentStatus;

    @OneToMany(mappedBy = "tournament")
    private List<TournamentTeam> tournamentTeams;
}
