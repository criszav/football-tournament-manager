package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.TokenJwt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenJwtRepository extends JpaRepository<TokenJwt, Long> {

    Optional<TokenJwt> findByToken(String jwt);
}
