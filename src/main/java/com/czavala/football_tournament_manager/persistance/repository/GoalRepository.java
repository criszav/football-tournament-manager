package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.dto.goal.GetGoalResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.Goal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal,Long> {

    Page<Goal> findByTournamentIdAndTeamId(Long tournamentId, Long teamId, Pageable pageable);

    Page<Goal> findByTournamentId(Long tournamentId, Pageable pageable);

    Page<Goal> findByPlayerIdAndTournamentId(Long tournamentId, Long playerId, Pageable pageable);

    Page<Goal> findByPlayerId(Long playerId, Pageable pageable);

    Page<Goal> findByPlayerIdAndTeamId(Long playerId, Long teamId, Pageable pageable);
}
