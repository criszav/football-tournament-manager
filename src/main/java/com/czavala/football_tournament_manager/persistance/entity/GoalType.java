package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_goal_type")
public class GoalType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", unique = true, length = 50, nullable = false)
    private String typeName;

    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "goalType")
    private List<Goal> goals;
}
