package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "t_tournament_team",
        uniqueConstraints = @UniqueConstraint(
                // Constraint indica que un equipo no puede repetirse en un mismo torneo
                name = "UK_TEAM_TOURNAMENT",
                columnNames = {"tournament_id", "team_id"})
)
public class TournamentTeam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tournament_id", nullable = false)
    private Long tournamentId;

    @ManyToOne
    @JoinColumn(name = "tournament_id", insertable = false, updatable = false)
    private Tournament tournament;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime lastModified;

}
