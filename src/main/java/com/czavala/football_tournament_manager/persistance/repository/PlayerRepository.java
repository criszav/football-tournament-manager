package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Page<Player> findByTeamId(Long teamId, Pageable pageable);

    boolean existsBySquadNumberAndTeamId(Integer squadNumber, Long teamId);
}
