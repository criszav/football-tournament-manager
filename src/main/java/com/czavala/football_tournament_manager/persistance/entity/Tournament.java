package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

import java.util.Date;

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
}
