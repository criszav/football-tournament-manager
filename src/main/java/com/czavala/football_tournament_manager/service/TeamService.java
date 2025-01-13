package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.player.PlayerTeamDto;
import com.czavala.football_tournament_manager.dto.team.SaveTeamDto;
import com.czavala.football_tournament_manager.dto.team.TeamResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamService {
    
    Page<TeamResponseDto> findAllTeams(Pageable pageable);

    TeamResponseDto findOneById(Long teamId);

    TeamResponseDto createOne(SaveTeamDto saveTeamDto);

    TeamResponseDto updateOneById(Long teamId, SaveTeamDto saveTeamDto);

    void disableOneById(Long teamId);

    Team findTeamEntityById(Long teamId);

    Page<CardTournamentResponseDto> findAllCardsByTeam(Long teamId, Pageable pageable);

    Page<PlayerTeamDto> findAllPlayers(Long teamId, Pageable pageable);
}
