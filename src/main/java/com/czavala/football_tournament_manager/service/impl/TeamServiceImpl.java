package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.player.PlayerTeamDto;
import com.czavala.football_tournament_manager.dto.team.SaveTeamDto;
import com.czavala.football_tournament_manager.dto.team.TeamResponseDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.card.CardMapper;
import com.czavala.football_tournament_manager.mapper.player.PlayerMapper;
import com.czavala.football_tournament_manager.mapper.team.TeamMapper;
import com.czavala.football_tournament_manager.persistance.entity.Team;
import com.czavala.football_tournament_manager.persistance.repository.CardRepository;
import com.czavala.football_tournament_manager.persistance.repository.PlayerRepository;
import com.czavala.football_tournament_manager.persistance.repository.TeamRepository;
import com.czavala.football_tournament_manager.service.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final CardRepository cardRepository;
    private final PlayerRepository playerRepository;

    public TeamServiceImpl(TeamRepository teamRepository, CardRepository cardRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.cardRepository = cardRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TeamResponseDto> findAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable)
                .map(TeamMapper::mapToTeamResponseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public TeamResponseDto findOneById(Long teamId) {
        return TeamMapper.mapToTeamResponseDto(this.findTeamEntityById(teamId));
    }

    @Transactional(readOnly = true)
    @Override
    public Team findTeamEntityById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team con ID " + teamId + " no encontrado"));
    }

    @Override
    public TeamResponseDto createOne(SaveTeamDto saveTeamDto) {
        Team newTeam = TeamMapper.mapToTeamEntity(saveTeamDto);
        teamRepository.save(newTeam);
        return TeamMapper.mapToTeamResponseDto(newTeam);
    }

    @Override
    public TeamResponseDto updateOneById(Long teamId, SaveTeamDto saveTeamDto) {
        Team teamFromDB = this.findTeamEntityById(teamId);
        teamFromDB.setName(saveTeamDto.getName());
        teamFromDB.setTeamCode(saveTeamDto.getTeamCode());
        teamFromDB.setImageUrl(saveTeamDto.getImageUrl());
        teamFromDB.setActive(saveTeamDto.getIsActive());
        teamFromDB.setEnabled(saveTeamDto.getIsEnabled());
        teamFromDB.setUserId(saveTeamDto.getUserId());
        teamRepository.save(teamFromDB);

        return TeamMapper.mapToTeamResponseDto(teamFromDB);
    }

    @Override
    public void disableOneById(Long teamId) {
        Team teamFromDB = this.findTeamEntityById(teamId);
        teamFromDB.setActive(false);
        teamRepository.save(teamFromDB);
    }

    @Override
    public Page<CardTournamentResponseDto> findAllCardsByTeam(Long teamId, Pageable pageable) {
        return cardRepository.findByTeamId(teamId, pageable)
                .map(CardMapper::mapToCardTournamentDto);
    }

    @Override
    public Page<PlayerTeamDto> findAllPlayers(Long teamId, Pageable pageable) {
        return playerRepository.findByTeamId(teamId, pageable)
                .map(PlayerMapper::mapToPlayerTeamDto);
    }
}
