package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
        name = "t_player",
        // no puede haber dos jugadores con el mismo numero de camiseta en un mismo equipo
        uniqueConstraints = @UniqueConstraint(
                name = "UK_PLAYER_SQUAD_TEAM",
                columnNames = {"squad_number", "team_id"})
)
public class Player {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false, length = 9)
    private String run;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(length = 40, unique = true)
    private String nickname;

    @Column(name = "image_url", length = 2000)
    private String imageUrl;

    @Check(constraints = "squad_number BETWEEN 1 AND 99")
    @Column(name = "squad_number", nullable = false)
    private Integer squadNumber;

    // indica si el jugador esta activo en el sistema
    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    // indica si un jugador esta habilitado para jugar
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @OneToMany(mappedBy = "player")
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "player")
    private List<Card> cards;

    @OneToMany(mappedBy = "player")
    private List<Goal> goals;

    @Column(name = "team_id")
    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    @OneToMany(mappedBy = "player")
    private List<TeamPlayer> teamsPlayer;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime lastModified;

}
