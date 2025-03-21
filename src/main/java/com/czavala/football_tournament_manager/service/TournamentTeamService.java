package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.tournament.AddedTeamDto;

public interface TournamentTeamService {

    AddedTeamDto addTeamToTournament(Long tournamentId, Long teamId);

    void removeTeamFromTournament(Long tournamentTeamId);
}
