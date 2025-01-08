package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.persistance.entity.Team;
import com.czavala.football_tournament_manager.persistance.entity.TournamentTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TournamentTeamRepository extends JpaRepository<TournamentTeam, Long> {

    @Query("SELECT t FROM TournamentTeam tt JOIN tt.team t WHERE tt.tournament.id = :tournamentId")
    Page<Team> findTeamByTournamentId(Long tournamentId, Pageable pageable);



}
