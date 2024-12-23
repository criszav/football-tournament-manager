package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_goal")
public class Goal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int minute;

    @Column(name = "player_id", nullable = false)
    private Long playerId;

    @Column(name = "team_id", nullable = false)
    private Long teamId;

    @Column(name = "match_id", nullable = false)
    private Long matchId;

    @Column(name = "goal_type_id", nullable = false)
    private Long goalTypeId;

    @ManyToOne
    @JoinColumn(name = "player_id", insertable = false, updatable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "match_id", insertable = false, updatable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "goal_type_id", insertable = false, updatable = false)
    private GoalType goalType;

}
