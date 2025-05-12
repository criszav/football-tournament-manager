package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.player.PlayerTeamDto;
import com.czavala.football_tournament_manager.dto.team.SaveTeamDto;
import com.czavala.football_tournament_manager.dto.team.TeamResponseDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.card.CardMapper;
import com.czavala.football_tournament_manager.mapper.player.PlayerMapper;
import com.czavala.football_tournament_manager.mapper.team.TeamMapper;
import com.czavala.football_tournament_manager.persistance.entity.Team;
import com.czavala.football_tournament_manager.persistance.repository.CardRepository;
import com.czavala.football_tournament_manager.persistance.repository.PlayerRepository;
import com.czavala.football_tournament_manager.persistance.repository.TeamRepository;
import com.czavala.football_tournament_manager.service.TeamService;
import com.czavala.football_tournament_manager.service.cloudinary.CloudinaryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final CardRepository cardRepository;
    private final PlayerRepository playerRepository;
    private final CloudinaryService cloudinaryService;

    public TeamServiceImpl(TeamRepository teamRepository,
                           CardRepository cardRepository,
                           PlayerRepository playerRepository,
                           CloudinaryService cloudinaryService) {
        this.teamRepository = teamRepository;
        this.cardRepository = cardRepository;
        this.playerRepository = playerRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<TeamResponseDto> findAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable)
                .map(TeamMapper::mapToTeamResponseDto);
    }

    @Cacheable(value = "teams", key = "#teamId") // Guarda el resultado en caché
    @Transactional(readOnly = true)
    @Override
    public TeamResponseDto findOneById(Long teamId) {
        return TeamMapper.mapToTeamResponseDto(this.findTeamEntityById(teamId));
    }

    @Transactional(readOnly = true)
    @Override
    public Team findTeamEntityById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team con ID " + teamId + " no encontrado"));
    }

    @Override
    public TeamResponseDto createOne(SaveTeamDto teamDto) {

        Team newTeam = new Team();
        newTeam.setName(teamDto.getName());
        newTeam.setTeamCode(teamDto.getTeamCode().toUpperCase());
        newTeam.setActive(teamDto.getIsActive());
        newTeam.setEnabled(teamDto.getIsEnabled());
        newTeam.setUserId(teamDto.getUserId());

        // Sube imagen a Cloudinary
        // Retorna url de imagen, de lo contrario retorna null
        String imageUrl = cloudinaryService.uploadImageToCloudinary(
                teamDto.getImageFile(),
                "teams", // Indica que imagen se guardará en carpeta 'teams' en Cloudinary
                teamDto.getTeamCode() // Asignará código del equipo al nombre de la imagen
        );

        newTeam.setImageUrl(imageUrl);
        newTeam.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));

        teamRepository.save(newTeam);
        return TeamMapper.mapToTeamResponseDto(newTeam);
    }

    @CachePut(value = "teams", key = "#teamId") // Actualiza data en el caché
    @Override
    public TeamResponseDto updateOneById(Long teamId, SaveTeamDto teamDto) {
        Team teamFromDB = this.findTeamEntityById(teamId);

        teamFromDB.setName(teamDto.getName());
        teamFromDB.setTeamCode(teamDto.getTeamCode());
        teamFromDB.setActive(teamDto.getIsActive());
        teamFromDB.setEnabled(teamDto.getIsEnabled());
        teamFromDB.setUserId(teamDto.getUserId());

        // Verifica si la petición contiene una nueva imagen
        // Si petición NO contiene una nueva imagen entonces se mantiene url de imagen existente
        if (teamDto.getImageFile() != null && !teamDto.getImageFile().isEmpty()) {

            // Sube nueva imagen a Cloudinary y obtiene nueva url
            String newImageUrl = cloudinaryService.uploadImageToCloudinary(
                    teamDto.getImageFile(),
                    "teams", // Nombre carpeta donde se guardará nueva imagen en Cloudinary
                    teamFromDB.getName()
            );

            // Actualiza nueva url de la imagen del team en db
            teamFromDB.setImageUrl(newImageUrl);
        }
        teamFromDB.setLastModified(LocalDateTime.now(ZoneId.systemDefault()));

        teamRepository.save(teamFromDB);

        return TeamMapper.mapToTeamResponseDto(teamFromDB);
    }

    @CacheEvict(value = "teams", key = "#teamId") // Elimina el dato del caché
    @Override
    public void disableOneById(Long teamId) {
        Team teamFromDB = this.findTeamEntityById(teamId);
        teamFromDB.setActive(false);
        teamRepository.save(teamFromDB);
    }

    @Override
    public Page<CardTournamentResponseDto> findAllCardsByTeam(Long teamId, Pageable pageable) {
        return cardRepository.findByTeamId(teamId, pageable)
                .map(CardMapper::mapToCardTournamentDto);
    }

    @Override
    public Page<PlayerTeamDto> findAllPlayers(Long teamId, Pageable pageable) {
        return playerRepository.findByTeamId(teamId, pageable)
                .map(PlayerMapper::mapToPlayerTeamDto);
    }
}
