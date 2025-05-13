package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.stats.*;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.persistance.entity.Player;
import com.czavala.football_tournament_manager.persistance.entity.Team;
import com.czavala.football_tournament_manager.persistance.repository.CardRepository;
import com.czavala.football_tournament_manager.persistance.repository.GoalRepository;
import com.czavala.football_tournament_manager.persistance.repository.PlayerRepository;
import com.czavala.football_tournament_manager.persistance.repository.TeamRepository;
import com.czavala.football_tournament_manager.persistance.utils.*;
import com.czavala.football_tournament_manager.service.StatsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsServiceImpl implements StatsService {

    private final GoalRepository goalRepository;
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final CardRepository cardRepository;

    public StatsServiceImpl(GoalRepository goalRepository,
                            PlayerRepository playerRepository,
                            TeamRepository teamRepository,
                            CardRepository cardRepository) {
        this.goalRepository = goalRepository;
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.cardRepository = cardRepository;
    }

    @Cacheable(value = "tournament_scorers", key = "#tournamentId")
    @Override
    public List<ScorerDto> getScorersByTournament(Long tournamentId) {

        // Obtiene todos los jugadores que hayan anotado goles en un determinado torneo
        List<PlayerGoals> playerGoals = goalRepository.findGoalsScoredGroupByPlayer(tournamentId);

        // Mapea resultados a dto ScorerDto
        return playerGoals.stream()
                .map(pg -> {

                    // Obtiene desde db a jugador que haya anotado gol en el torneo
                    Player player = playerRepository.findById(pg.getPlayerId())
                            .orElseThrow(() -> new ResourceNotFoundException("Player not found. Player id: " + pg.getPlayerId()));

                    // Crea dto ScorerDto con informacion de goles anotadospor jugador
                    return ScorerDto.builder()
                            .playerFirstname(player.getFirstname())
                            .playerLastname(player.getLastname())
                            .team(player.getTeam().getName())
                            .goals(pg.getGoals())
                            .build();
                })
                // ordena por cantidad de goles en forma descendente
                .sorted((s1, s2) -> Integer.compare(s2.getGoals(), s1.getGoals()))
                .collect(Collectors.toList());

    }

    @Cacheable(value = "tournament_team_goals", key = "#tournamentId")
    @Override
    public List<TeamGoalsScoredDto> getGoalsScoredByTeam(Long tournamentId) {

        // Obtiene los goles anotados por un equipo en un determinado torneo, agrupados por equipo
        List<TeamGoals> teamGoals = goalRepository.findGoalsScoredGroupByTeam(tournamentId);

        return teamGoals.stream()
                .map(tg -> {

                    // Obtiene desde db a equipos en listado de goles
                    Team team = teamRepository.findById(tg.getTeamId())
                            .orElseThrow(() -> new ResourceNotFoundException("Team not found. Team id: " + tg.getTeamId()));

                    // Crea dto con informacion de goles anotados por equipo
                    return TeamGoalsScoredDto.builder()
                            .teamName(team.getName())
                            .goals(tg.getGoals())
                            .build();
                })
                // Ordena por cantidad de goles en forma descendente
                .sorted((s1, s2) -> Integer.compare(s2.getGoals(), s1.getGoals()))
                .collect(Collectors.toList());
    }

    @Override
    public List<RedCardListByTournamentDto> getRedCardsByTournament(Long tournamentId) {

        // Obtiene listado de jugadores con tarjetas rojas en un determinado torneo
        List<PlayerRedCards> playerRedCards = cardRepository.findRedCardsGroupByPlayer(tournamentId);

        //playerRedCards.forEach(plc -> System.out.println("Player id: " + plc.getPlayerId() + " | Total red cards: " + plc.getTotalRedCards() + " | Team id: " + plc.getTeamId()));

        return playerRedCards.stream()
                .map(pc -> {

                    Player player = playerRepository.findById(pc.getPlayerId())
                            .orElseThrow(() -> new ResourceNotFoundException("Player not found. Player id: " + pc.getPlayerId()));

                    return RedCardListByTournamentDto.builder()
                            .playerFirstname(player.getFirstname())
                            .playerLastname(player.getLastname())
                            .totalRedCards(pc.getTotalRedCards())
                            //todo - ¿qué pasa si el jugador cambió de equipo? ¿traer el nombre del equipo con el cual le dieron la roja o trae el nombre del nuevo equipo?
                            .teamName(pc.getTeamName())
                            .build();

                })
                .sorted((s1, s2) -> Integer.compare(s2.getTotalRedCards(), s1.getTotalRedCards()))
                .collect(Collectors.toList());
    }

    @Override
    public List<YellowCardListByTournamentDto> getYellowCardsByTournament(Long tournamentId) {

        // Obtiene listado de jugadores con tarjetas rojas en un determinado torneo
        List<PlayerYellowCards> playerYellowCards = cardRepository.findYellowCardsGroupByPlayer(tournamentId);

        return playerYellowCards.stream()
                .map(pc -> {

                    // Obtiene desde db player con tarjeta amarilla
                    Player player = playerRepository.findById(pc.getPlayerId())
                            .orElseThrow(() -> new ResourceNotFoundException("Player not found. Player id: " + pc.getPlayerId()));

                    // Mapea a listado de dto con jugadores que tengan tarjetas amarillas
                    return YellowCardListByTournamentDto.builder()
                            .playerFirstname(player.getFirstname())
                            .playerLastname(player.getLastname())
                            .totalYellowCards(pc.getTotalYellowCards())
                            .teamName(pc.getTeamName())
                            .build();

                })
                // Ordena listado en forma descendente por cantidad de tarjetas amarillas
                .sorted((s1, s2) -> Integer.compare(s2.getTotalYellowCards(), s1.getTotalYellowCards()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamRedCardsDto> getRedCardListByTeam(Long tournamentId) {

        // Obtiene listado de tarjetas rojas agrupadas por equipo en un determinado torneo segun id
        List<TeamRedCards> teamRedCards = cardRepository.findRedCardsGroupByTeam(tournamentId);

        return teamRedCards.stream()
                .map(rc -> {

                    return TeamRedCardsDto.builder()
                            .teamName(rc.getTeamName())
                            .totalRedCards(rc.getTotalRedCards())
                            .build();
                })
                .sorted((s1, s2) -> Integer.compare(s2.getTotalRedCards(), s1.getTotalRedCards()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamYellowCardsDto> getYellowCardListByTeam(Long tournamentId) {

        // Obtiene listado de tarjetas amarillas agrupadas por equipo en un determinado torneo segun id
        List<TeamYellowCards> teamYellowCards = cardRepository.findYellowCardsGroupByTeam(tournamentId);

        return teamYellowCards.stream()
                .map(rc -> {

                    return TeamYellowCardsDto.builder()
                            .teamName(rc.getTeamName())
                            .totalYellowCards(rc.getTotalYellowCards())
                            .build();
                })
                .sorted((s1, s2) -> Integer.compare(s2.getTotalYellowCards(), s1.getTotalYellowCards()))
                .collect(Collectors.toList());
    }
}
