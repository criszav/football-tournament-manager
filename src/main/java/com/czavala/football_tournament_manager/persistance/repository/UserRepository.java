package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
