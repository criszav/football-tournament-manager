package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.stats.*;

import java.util.List;

public interface StatsService {

    List<ScorerDto> getScorersByTournament(Long tournamentId);

    List<TeamGoalsScoredDto> getGoalsScoredByTeam(Long tournamentId);

    List<RedCardListByTournamentDto> getRedCardsByTournament(Long tournamentId);

    List<YellowCardListByTournamentDto> getYellowCardsByTournament(Long tournamentId);

    List<TeamRedCardsDto> getRedCardListByTeam(Long tournamentId);

    List<TeamYellowCardsDto> getYellowCardListByTeam(Long tournamentId);
}
