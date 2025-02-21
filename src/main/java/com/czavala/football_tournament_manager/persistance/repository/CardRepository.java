package com.czavala.football_tournament_manager.persistance.repository;

import com.czavala.football_tournament_manager.dto.card.CardResponseDto;
import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.Card;
import com.czavala.football_tournament_manager.persistance.utils.PlayerRedCards;
import com.czavala.football_tournament_manager.persistance.utils.PlayerYellowCards;
import com.czavala.football_tournament_manager.persistance.utils.TeamRedCards;
import com.czavala.football_tournament_manager.persistance.utils.TeamYellowCards;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    Page<Card> findByTournamentId(Long tournamentId, Pageable pageable);

    Page<Card> findByTournamentIdAndTeamId(Long tournamentId, Long teamId, Pageable pageable);

    Page<Card> findByTeamId(Long teamId, Pageable pageable);

    Page<Card> findByCardType(String cardType, Pageable pageable);

    Page<Card> findByTournamentIdAndPlayerId(Long tournamentId, Long playerId, Pageable pageable);

    @Query("SELECT c.playerId as playerId, COUNT(c.playerId) as totalRedCards, c.team.name as teamName " +
            "FROM Card c " +
            "WHERE c.tournamentId = :tournamentId AND c.cardType = 'RED' " +
            "GROUP BY c.playerId, c.teamId")
    List<PlayerRedCards> findRedCardsGroupByPlayer(@PathVariable("tournamentId") Long tournamentId);

    @Query("SELECT c.playerId as playerId, COUNT(c.playerId) as totalYellowCards, c.team.name as teamName " +
            "FROM Card c " +
            "WHERE c.tournamentId = :tournamentId AND c.cardType = 'YELLOW' " +
            "GROUP BY c.playerId, c.teamId")
    List<PlayerYellowCards> findYellowCardsGroupByPlayer(@PathVariable("tournamentId") Long tournamentId);

    @Query("SELECT c.teamId as teamId, COUNT(c.playerId) as totalRedCards, c.team.name as teamName " +
            "FROM Card c " +
            "WHERE c.tournamentId = :tournamentId AND c.cardType = 'RED' " +
            "GROUP BY c.teamId, c.team.name")
    List<TeamRedCards> findRedCardsGroupByTeam(Long tournamentId);

    @Query("SELECT c.teamId as teamId, COUNT(c.playerId) as totalYellowCards, c.team.name as teamName " +
            "FROM Card c " +
            "WHERE c.tournamentId = :tournamentId AND c.cardType = 'YELLOW' " +
            "GROUP BY c.teamId, c.team.name")
    List<TeamYellowCards> findYellowCardsGroupByTeam(Long tournamentId);
}
