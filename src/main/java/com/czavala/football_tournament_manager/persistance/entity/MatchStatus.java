package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_match_status")
public class MatchStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", length = 50, nullable = false, unique = true)
    private String statusName;

    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "matchStatus")
    private List<Match> matches;
}
