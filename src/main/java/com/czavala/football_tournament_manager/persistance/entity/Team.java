package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

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

}
