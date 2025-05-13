package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.match.CreatedMatchDto;
import com.czavala.football_tournament_manager.dto.match.MatchResponseDto;
import com.czavala.football_tournament_manager.dto.match.SaveMatchDto;
import com.czavala.football_tournament_manager.exception.InvalidMatchException;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.match.MatchMapper;
import com.czavala.football_tournament_manager.persistance.entity.Match;
import com.czavala.football_tournament_manager.persistance.repository.MatchRepository;
import com.czavala.football_tournament_manager.service.MatchService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@Service
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    public MatchServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MatchResponseDto> findAllMatches(Pageable pageable) {
        return matchRepository.findAll(pageable)
                .map(MatchMapper::mapToMatchResponseDto);
    }

    @Cacheable(value = "matches", key = "#matchId")
    @Transactional(readOnly = true)
    @Override
    public MatchResponseDto findOneById(Long matchId) {
        return MatchMapper.mapToMatchResponseDto(this.findMatchEntityById(matchId));
    }

    @Override
    public CreatedMatchDto create(SaveMatchDto matchDto) {

        // Verifica que no exista previamente el match que se ingresa
        validateMatchExists(matchDto);
        // Verifica que equipos NO sean el mismo (un equipo no puede jugar contra sí mismo)
        validateTeams(matchDto);

        Match match = new Match();
        match.setMatchDate(matchDto.getMatchDate());
        match.setMatchKickOff(matchDto.getMatchKickOff());
        match.setMatchday(matchDto.getMatchday());
        match.setHomeTeamId(matchDto.getHomeTeamId());
        match.setAwayTeamId(matchDto.getAwayTeamId());
        match.setMatchStatusId(matchDto.getMatchStatusId());
        match.setHomeTeamGoals(matchDto.getHomeTeamGoals());
        match.setAwayTeamGoals(matchDto.getAwayTeamGoals());
        match.setTournamentId(matchDto.getTournamentId());
        match.setNotes(matchDto.getNotes());
        match.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        matchRepository.save(match);

        return MatchMapper.mapToCreatedMatchDto(match);
    }

    @CachePut(value = "matches", key = "#matchId")
    @Override
    public MatchResponseDto updateById(Long matchId, SaveMatchDto matchDto) {

        // Valida equipos de match que se actualiza
        // Verifica que equipos NO sean el mismo (un equipo no puede jugar contra sí mismo)
        validateTeams(matchDto);

        Match matchFromDB = this.findMatchEntityById(matchId);
        matchFromDB.setMatchDate(matchDto.getMatchDate());
        matchFromDB.setMatchKickOff(matchDto.getMatchKickOff());
        matchFromDB.setMatchday(matchDto.getMatchday());
        matchFromDB.setMatchStatusId(matchDto.getMatchStatusId());
        matchFromDB.setHomeTeamGoals(matchDto.getHomeTeamGoals());
        matchFromDB.setAwayTeamGoals(matchDto.getAwayTeamGoals());
        matchFromDB.setHomeTeamId(matchDto.getHomeTeamId());
        matchFromDB.setAwayTeamId(matchDto.getAwayTeamId());
        matchFromDB.setTournamentId(matchDto.getTournamentId());
        matchFromDB.setLastModified(LocalDateTime.now(ZoneId.systemDefault()));
        matchRepository.save(matchFromDB);

        return MatchMapper.mapToMatchResponseDto(matchFromDB);
    }

    @Override
    public Page<MatchResponseDto> findMatchesByMatchStatusId(Long matchStatusId, Pageable pageable) {
        return matchRepository.findByMatchStatusId(matchStatusId, pageable)
                .map(MatchMapper::mapToMatchResponseDto);
    }

    @Override
    public List<MatchResponseDto> findMatchesByTeamInTournament(Long tournamentId, Long teamId) {
        return matchRepository.findByTeamIdInTournament(tournamentId, teamId).stream()
                .map(MatchMapper::mapToMatchResponseDto)
                .toList();
    }

    @Override
    public List<MatchResponseDto> findByMatchdayInTournament(Long tournamentId, Integer matchday) {
        return matchRepository.findByTournamentIdAndMatchday(tournamentId, matchday).stream()
                .map(MatchMapper::mapToMatchResponseDto)
                .toList();
    }

    private Match findMatchEntityById(Long matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match no encontrado. Match id: " + matchId));
    }

    // Verifica si el Match que se está creando ya existe en el sistema
    public void validateMatchExists(SaveMatchDto matchDto) {

        // Verifica que no exista previamente el match entre ambos equipos en el mismo torneo
        boolean matchExists = matchRepository.existsByHomeTeamIdAndAwayTeamId(
                matchDto.getHomeTeamId(), matchDto.getAwayTeamId()
        );
        if (matchExists) {
            String errorMsg = "Error al crear partido. " +
                    "Es posible que ya exista un partido entre ambos equipos ingresados. " +
                    "Por favor verifique si el partido ya fue creado previamente.";
            throw new InvalidMatchException(errorMsg);
        }
    }

    // Valida que al ingresar/actualizar un match los equipos NO sean el mismo
    public void validateTeams(SaveMatchDto matchDto) {

        // Verifica que equipos asignados al partido NO sean el mismo equipo
        // Un equipo no puede jugar contra sí mismo
        if (matchDto.getHomeTeamId().equals(matchDto.getAwayTeamId())) {
            throw new InvalidMatchException("Error al crear partido. No se debe repetir el equipo.");
        }
    }
}
