package com.czavala.football_tournament_manager.mapper;

import com.czavala.football_tournament_manager.dto.tournament.SaveTournamentDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentResponseDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentTeamDto;
import com.czavala.football_tournament_manager.dto.user.ResponseUserDto;
import com.czavala.football_tournament_manager.persistance.entity.Tournament;
import com.czavala.football_tournament_manager.persistance.entity.TournamentTeam;
import com.czavala.football_tournament_manager.persistance.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class TournamentMapper {

    public static TournamentResponseDto mapToTournamentDto(Tournament tournament) {

        if (tournament == null) return null;

        return TournamentResponseDto
                .builder()
                .id(tournament.getId())
                .name(tournament.getName())
                .startDate(tournament.getStartDate())
                .endDate(tournament.getEndDate())
                .numberOfTeams(tournament.getNumberOfTeams())
                .isActive(tournament.isActive())
                .tournamentStatus(TournamentStatusMapper.mapToTournamentStatusDto(tournament.getTournamentStatus()))
                .user(mapToUserDto(tournament.getUser()))
                .createdAt(tournament.getCreatedAt())
                .lastModified(tournament.getLastModified())
                .build();
    }

    public static ResponseUserDto mapToUserDto(User user) {

        if (user == null) return null;

        return ResponseUserDto
                .builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .roleId(user.getUserRoleId())
                .isActive(user.isActive())
                .build();
    }

    public static List<TournamentTeamDto> mapToListTournamentTeamDto(List<TournamentTeam> tournamentTeams) {

        if (tournamentTeams == null) return null;

        return tournamentTeams.stream()
                .map(TournamentMapper::mapToTournamentTeamDto)
                .toList();
    }

    public static TournamentTeamDto mapToTournamentTeamDto(TournamentTeam tournamentTeam) {

        if (tournamentTeam == null) return null;

        return TournamentTeamDto
                .builder()
                .teamId(tournamentTeam.getTeamId())
                .teamName(tournamentTeam.getTeam().getName())
                .build();
    }

    public static Tournament mapToTournamentEntity(SaveTournamentDto saveTournamentDto) {

        if (saveTournamentDto == null) return null;

        Tournament tournament = new Tournament();
        tournament.setName(saveTournamentDto.getName());
        tournament.setStartDate(saveTournamentDto.getStartDate());
        tournament.setEndDate(saveTournamentDto.getEndDate());
        tournament.setNumberOfTeams(saveTournamentDto.getNumberOfTeams());
        tournament.setActive(saveTournamentDto.getIsActive());
        tournament.setUserId(saveTournamentDto.getUserId());
        tournament.setTournamentStatusId(saveTournamentDto.getTournamentStatusId());
        tournament.setCreatedAt(LocalDateTime.now());

        return tournament;
    }
}
