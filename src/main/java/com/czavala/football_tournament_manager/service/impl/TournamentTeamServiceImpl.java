package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.tournament.AddedTeamDto;
import com.czavala.football_tournament_manager.exception.InvalidTournamentTeamException;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.tournament.TournamentTeamMapper;
import com.czavala.football_tournament_manager.persistance.entity.TournamentTeam;
import com.czavala.football_tournament_manager.persistance.repository.TournamentTeamRepository;
import com.czavala.football_tournament_manager.service.TournamentTeamService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TournamentTeamServiceImpl implements TournamentTeamService {

    private final TournamentTeamRepository tournamentTeamRepository;

    public TournamentTeamServiceImpl(TournamentTeamRepository tournamentTeamRepository) {
        this.tournamentTeamRepository = tournamentTeamRepository;
    }

    @Override
    public AddedTeamDto addTeamToTournament(Long tournamentId, Long teamId) {

        validateTournamentTeam(tournamentId, teamId);

        TournamentTeam newTournamentTeam = new TournamentTeam();
        newTournamentTeam.setTournamentId(tournamentId);
        newTournamentTeam.setTeamId(teamId);
        newTournamentTeam.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        tournamentTeamRepository.save(newTournamentTeam);

        return TournamentTeamMapper.mapToAddedTeamDto(newTournamentTeam);
    }

    @Override
    public void removeTeamFromTournament(Long tournamentTeamId) {
        TournamentTeam tournamentTeam = this.findTournamentTeamEntity(tournamentTeamId);
        tournamentTeamRepository.delete(tournamentTeam);
    }

    // Valida si team ya fue agregado al torneo previamente
    private void validateTournamentTeam(Long tournamentId, Long teamId) {

        boolean existsTeamInTournament = tournamentTeamRepository
                                            .existsByTournamentIdAndTeamId(tournamentId, teamId);

        if (existsTeamInTournament) {
            throw new InvalidTournamentTeamException(
                    "Error, team con id " + teamId + " ya fue asignado al torneo con id " + tournamentId + ". " +
                    "Por favor verifique si el team ya existe en el torneo que se le quiere asignar."
            );
        }
    }

    private TournamentTeam findTournamentTeamEntity(Long tournamentTeamId) {
        return tournamentTeamRepository.findById(tournamentTeamId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "TournamentTeam not found. TournamentTeam id " + tournamentTeamId
                ));
    }
}
