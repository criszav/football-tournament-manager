package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Page<Match> findByMatchStatusId(Long matchStatusId, Pageable pageable);

    boolean existsByHomeTeamIdAndAwayTeamId(Long homeTeamId, Long awayTeamId);
}
