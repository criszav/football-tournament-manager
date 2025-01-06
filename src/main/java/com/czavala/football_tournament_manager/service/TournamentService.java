package com.czavala.football_tournament_manager.service;

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

    TournamentResponseDto findOneById(Long tournamentId);

    TournamentResponseDto createOne(SaveTournamentDto saveTournamentDto);

    TournamentResponseDto updateOneById(Long tournamentId, SaveTournamentDto saveTournamentDto);

    void disableOneById(Long tournamentId);

    List<Team> findAllTeamsByTournamentId(Long tournamentId);

    List<Card> findAllCardsByTournamentId(Long tournamentId);

    List<Card> findTeamCardsByTournamentId(Long tournamentId, Long teamId);

    Tournament findTournamentEntityById(Long tournamentId);
}
