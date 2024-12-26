package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.TournamentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentStatusRepository extends JpaRepository <TournamentStatus, Long> {
}
