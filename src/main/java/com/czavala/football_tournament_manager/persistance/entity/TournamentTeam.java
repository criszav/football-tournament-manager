package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "t_tournament_team",
        uniqueConstraints = @UniqueConstraint(columnNames = {"tournament_id", "team_id"})
)
public class TournamentTeam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tournament_id", nullable = false)
    private Long tournamentId;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

}
