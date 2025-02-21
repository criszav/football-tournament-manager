package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.Goal;
import com.czavala.football_tournament_manager.persistance.utils.PlayerGoals;
import com.czavala.football_tournament_manager.persistance.utils.TeamGoals;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal,Long> {

    Page<Goal> findByTournamentIdAndTeamId(Long tournamentId, Long teamId, Pageable pageable);

    Page<Goal> findByTournamentId(Long tournamentId, Pageable pageable);

    Page<Goal> findByPlayerIdAndTournamentId(Long tournamentId, Long playerId, Pageable pageable);

    Page<Goal> findByPlayerId(Long playerId, Pageable pageable);

    Page<Goal> findByPlayerIdAndTeamId(Long playerId, Long teamId, Pageable pageable);

    // Obtiene listado de goleadores de un determinado torneo
    @Query("SELECT g.player.id as playerId, COUNT(g.id) as goals " +
            "FROM Goal g " +
            "WHERE g.tournament.id = :tournamentId " +
            "GROUP BY g.player.id")
    List<PlayerGoals> findGoalsScoredGroupByPlayer(@Param("tournamentId") Long tournamentId);

    // Obtiene listado de equipos por goles anotados en un determinado torneo
    @Query("SELECT g.teamId as teamId, COUNT(g.teamId) as goals " +
            "FROM Goal g " +
            "WHERE g.tournamentId = :tournamentId " +
            "GROUP BY g.teamId")
    List<TeamGoals> findGoalsScoredGroupByTeam(@Param("tournamentId") Long tournamentId);
}
