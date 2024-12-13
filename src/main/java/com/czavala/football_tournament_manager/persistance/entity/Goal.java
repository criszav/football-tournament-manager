package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_goal")
public class Goal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int minute;
}
