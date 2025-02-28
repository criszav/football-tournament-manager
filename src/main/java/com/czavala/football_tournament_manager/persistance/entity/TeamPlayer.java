package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(
        name = "t_team_player",
        uniqueConstraints = @UniqueConstraint(
                // Constraint indica que un jugador solo puede estar en un equipo a la vez en un torneo
                name = "UK_PLAYER_TEAM_TOURNAMENT",
                columnNames = {"player_id", "team_id", "tournament_id"})
)
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

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "tournament_id", nullable = false)
    private Long tournamentId;

    @ManyToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "tournament_id", insertable = false, updatable = false)
    private Tournament tournament;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime lastModified;
}
