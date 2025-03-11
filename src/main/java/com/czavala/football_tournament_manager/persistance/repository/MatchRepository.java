package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Page<Match> findByMatchStatusId(Long matchStatusId, Pageable pageable);

    boolean existsByHomeTeamIdAndAwayTeamId(Long homeTeamId, Long awayTeamId);

    @Query("SELECT m " +
            "FROM Match m " +
            "WHERE m.tournamentId = :tournamentId AND (m.homeTeamId = :teamId OR m.awayTeamId = :teamId)")
    List<Match> findByTeamIdInTournament(Long tournamentId, Long teamId);

    List<Match> findByTournamentIdAndMatchday(Long tournamentId, Integer matchday);
}
