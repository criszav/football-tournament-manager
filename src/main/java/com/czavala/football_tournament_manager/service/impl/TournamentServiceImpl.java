package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.tournament.SaveTournamentDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentResponseDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.TournamentMapper;
import com.czavala.football_tournament_manager.persistance.entity.Card;
import com.czavala.football_tournament_manager.persistance.entity.Team;
import com.czavala.football_tournament_manager.persistance.entity.Tournament;
import com.czavala.football_tournament_manager.persistance.repository.TournamentRepository;
import com.czavala.football_tournament_manager.service.TournamentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TournamentResponseDto> findAllTournaments(Pageable pageable) {
        return tournamentRepository.findAll(pageable)
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

    @Override
    public List<Team> findAllTeamsByTournamentId(Long tournamentId) {
        return null;
    }

    @Override
    public List<Card> findAllCardsByTournamentId(Long tournamentId) {
        return null;
    }

    @Override
    public List<Card> findTeamCardsByTournamentId(Long tournamentId, Long teamId) {
        return null;
    }
}
