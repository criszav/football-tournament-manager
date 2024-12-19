package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_tournament_status")
public class TournamentStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", unique = true, nullable = false, length = 50)
    private String statusName;

    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "tournamentStatus")
    private List<Tournament> tournaments;
}
