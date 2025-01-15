package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.goal.GetGoalResponseDto;
import com.czavala.football_tournament_manager.dto.team.TeamTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.tournament.SaveTournamentDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentResponseDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.card.CardMapper;
import com.czavala.football_tournament_manager.mapper.goal.GoalMapper;
import com.czavala.football_tournament_manager.mapper.tournament.TournamentMapper;
import com.czavala.football_tournament_manager.mapper.team.TeamMapper;
import com.czavala.football_tournament_manager.persistance.entity.Tournament;
import com.czavala.football_tournament_manager.persistance.repository.CardRepository;
import com.czavala.football_tournament_manager.persistance.repository.GoalRepository;
import com.czavala.football_tournament_manager.persistance.repository.TournamentRepository;
import com.czavala.football_tournament_manager.persistance.repository.TournamentTeamRepository;
import com.czavala.football_tournament_manager.service.TournamentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentTeamRepository tournamentTeamRepository;
    private final CardRepository cardRepository;
    private final GoalRepository goalRepository;

    public TournamentServiceImpl(
            TournamentRepository tournamentRepository,
            TournamentTeamRepository tournamentTeamRepository,
            CardRepository cardRepository, GoalRepository goalRepository) {

        this.tournamentRepository = tournamentRepository;
        this.tournamentTeamRepository = tournamentTeamRepository;
        this.cardRepository = cardRepository;
        this.goalRepository = goalRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TournamentResponseDto> findAllTournaments(Pageable pageable) {
        return tournamentRepository.findAll(pageable)
                .map(TournamentMapper::mapToTournamentDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TournamentResponseDto> findAllActiveTournaments(Pageable pageable) {
        return tournamentRepository.findByIsActiveTrue(pageable)
                .map(TournamentMapper::mapToTournamentDto);
    }

    @Transactional(readOnly = true)
    @Override
    public TournamentResponseDto findOneById(Long tournamentId) {
        return TournamentMapper.mapToTournamentDto(this.findTournamentEntityById(tournamentId));
    }

    @Transactional(readOnly = true)
    @Override
    public Tournament findTournamentEntityById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new ResourceNotFoundException("Torneo con ID " + tournamentId + " no encontrado"));
    }

    @Override
    public TournamentResponseDto createOne(SaveTournamentDto saveTournamentDto) {
        Tournament newTournament = TournamentMapper.mapToTournamentEntity(saveTournamentDto);
        tournamentRepository.save(newTournament);

        return TournamentMapper.mapToTournamentDto(newTournament);
    }

    @Override
    public TournamentResponseDto updateOneById(Long tournamentId, SaveTournamentDto saveTournamentDto) {
        Tournament tournamentFromDB = this.findTournamentEntityById(tournamentId);
        tournamentFromDB.setName(saveTournamentDto.getName());
        tournamentFromDB.setStartDate(saveTournamentDto.getStartDate());
        tournamentFromDB.setEndDate(saveTournamentDto.getEndDate());
        tournamentFromDB.setNumberOfTeams(saveTournamentDto.getNumberOfTeams());
        tournamentFromDB.setActive(saveTournamentDto.getIsActive());
        tournamentFromDB.setUserId(saveTournamentDto.getUserId());
        tournamentFromDB.setTournamentStatusId(saveTournamentDto.getTournamentStatusId());
        tournamentFromDB.setLastModified(LocalDateTime.now());

        tournamentRepository.save(tournamentFromDB);

        return TournamentMapper.mapToTournamentDto(tournamentFromDB);
    }

    @Override
    public void disableOneById(Long tournamentId) {
        Tournament tournamentFromDB = this.findTournamentEntityById(tournamentId);
        tournamentFromDB.setActive(false);
        tournamentRepository.save(tournamentFromDB);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TeamTournamentResponseDto> findAllTeamsByTournamentId(Long tournamentId, Pageable pageable) {

        return tournamentTeamRepository.findTeamByTournamentId(tournamentId, pageable)
                .map(TeamMapper::mapToTeamTournamentDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CardTournamentResponseDto> findAllCardsByTournamentId(Long tournamentId, Pageable pageable) {
        return cardRepository.findByTournamentId(tournamentId, pageable)
                .map(CardMapper::mapToCardTournamentDto);
    }

    @Override
    public Page<CardTournamentResponseDto> findTeamCardsByTournamentId(Long tournamentId, Long teamId, Pageable pageable) {
        return cardRepository.findByTournamentIdAndTeamId(tournamentId, teamId, pageable)
                .map(CardMapper::mapToCardTournamentDto);
    }

    @Override
    public Page<GetGoalResponseDto> findTeamGoalsByTournamentId(Long tournamentId, Long teamId, Pageable pageable) {
        return goalRepository.findByTournamentIdAndTeamId(tournamentId, teamId, pageable)
                .map(GoalMapper::mapToGoalDto);
    }

    @Override
    public Page<GetGoalResponseDto> findGoalsByTournamentId(Long tournamentId, Pageable pageable) {
        return goalRepository.findByTournamentId(tournamentId, pageable)
                .map(GoalMapper::mapToGoalDto);
    }

    @Override
    public Page<GetGoalResponseDto> findPlayerGoalsByTournamentId(Long tournamentId, Long playerId, Pageable pageable) {
        return goalRepository.findByPlayerIdAndTournamentId(tournamentId, playerId, pageable)
                .map(GoalMapper::mapToGoalDto);
    }
}
