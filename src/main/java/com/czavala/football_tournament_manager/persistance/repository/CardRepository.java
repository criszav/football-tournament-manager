package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    Page<Card> findByTournamentId(Long tournamentId, Pageable pageable);

    Page<Card> findByTournamentIdAndTeamId(Long tournamentId, Long teamId, Pageable pageable);
}
