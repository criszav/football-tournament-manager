package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "t_attendance")
public class Attendance {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_present", nullable = false)
    private boolean isPresent;

    private String notes;
}
