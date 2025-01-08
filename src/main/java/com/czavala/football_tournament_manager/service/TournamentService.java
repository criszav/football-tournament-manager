package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.team.TeamTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.tournament.SaveTournamentDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.Card;
import com.czavala.football_tournament_manager.persistance.entity.Team;
import com.czavala.football_tournament_manager.persistance.entity.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TournamentService {

    Page<TournamentResponseDto> findAllTournaments(Pageable pageable);

    Page<TournamentResponseDto> findAllActiveTournaments(Pageable pageable);

    TournamentResponseDto findOneById(Long tournamentId);

    TournamentResponseDto createOne(SaveTournamentDto saveTournamentDto);

    TournamentResponseDto updateOneById(Long tournamentId, SaveTournamentDto saveTournamentDto);

    void disableOneById(Long tournamentId);

    Page<TeamTournamentResponseDto> findAllTeamsByTournamentId(Long tournamentId, Pageable pageable);

    Page<CardTournamentResponseDto> findAllCardsByTournamentId(Long tournamentId, Pageable pageable);

    Page<CardTournamentResponseDto> findTeamCardsByTournamentId(Long tournamentId, Long teamId, Pageable pageable);

    Tournament findTournamentEntityById(Long tournamentId);
}
