package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
