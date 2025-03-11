package com.czavala.football_tournament_manager.persistance.entity;

import com.czavala.football_tournament_manager.exception.InvalidMatchException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(
        name = "t_match",
        uniqueConstraints = @UniqueConstraint(
                // Constraint para verificar que un partido no se repita
                name = "UK_HOME_TEAM_AWAY_TEAM",
                columnNames = {"home_team_id", "away_team_id"})
)
public class Match {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "match_date")
    private Date matchDate;

    @Column(name = "match_kick_off")
    private LocalTime matchKickOff;

    // Indica a que fecha del torneo corresponde un match
    private Integer matchday;

    @Column(name = "home_team_goals")
    private Integer homeTeamGoals;

    @Column(name = "away_team_goals")
    private Integer awayTeamGoals;

    @OneToMany(mappedBy = "match")
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "match")
    private List<Card> cards;

    @OneToMany(mappedBy = "match")
    private List<Goal> goals;

    @Column(name = "tournament_id", nullable = false)
    private Long tournamentId;

    @ManyToOne
    @JoinColumn(name = "tournament_id", insertable = false, updatable = false)
    private Tournament tournament;

    @Column(name = "match_status_id", nullable = false)
    private Long matchStatusId;

    @ManyToOne
    @JoinColumn(name = "match_status_id", insertable = false, updatable = false)
    private MatchStatus matchStatus;

    @Column(name = "home_team_id", nullable = false)
    private Long homeTeamId;

    @ManyToOne
    @JoinColumn(name = "home_team_id", insertable = false, updatable = false)
    private Team homeTeam;

    @Column(name = "away_team_id", nullable = false)
    private Long awayTeamId;

    @ManyToOne
    @JoinColumn(name = "away_team_id", insertable = false, updatable = false)
    private Team awayTeam;

    private String notes;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime lastModified;

    // antes de persistir o actualizar un dato verifica que no se ingrese un partido con el mismo equipo
    // Un equipo NO puede jugar contra si mismo
    @PrePersist
    @PreUpdate
    public void validateTeams() {

        if (this.homeTeamId == null || this.awayTeamId == null) {
            throw new InvalidMatchException("Error, equipos inválidos. Por favor verifique que los equipos sean válidos.");
        }

        if (this.homeTeamId.equals(this.awayTeamId)) {
            throw new InvalidMatchException("Error al crear partido e ingresar equipos. No se debe repetir el equipo.");
        }

    }

}
