package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "t_team_player")
// todo - agregar constraint unique para combinacion de playerId, teamId, tournamentId
public class TeamPlayer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_active", nullable = false)
    // indica si un jugador sigue activo en un equipo durante un determinado torneo
    private boolean isActive;
}
