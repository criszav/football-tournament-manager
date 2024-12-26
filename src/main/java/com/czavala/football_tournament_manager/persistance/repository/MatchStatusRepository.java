package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchStatusRepository extends JpaRepository<MatchStatus, Long> {
}
