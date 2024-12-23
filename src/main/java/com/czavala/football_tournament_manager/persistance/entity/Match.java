package com.czavala.football_tournament_manager.persistance.entity;

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
        uniqueConstraints = @UniqueConstraint(columnNames = {"home_team_id", "away_team_id"})
)
public class Match {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "match_date", nullable = false)
    private Date matchDate;

    @Column(name = "match_kick_off", nullable = false)
    private LocalTime matchKickOff;

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

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime lastModified;

}
