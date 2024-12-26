package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.GoalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalTypeRepository extends JpaRepository<GoalType, Long> {

    Optional<GoalType> findById(Long goalTypeId);

}
