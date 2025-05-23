package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.player.CreatedPlayerDto;
import com.czavala.football_tournament_manager.dto.player.PlayerResponseDto;
import com.czavala.football_tournament_manager.dto.player.SavePlayerDto;
import com.czavala.football_tournament_manager.exception.DuplicateSquadNumberException;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.player.PlayerMapper;
import com.czavala.football_tournament_manager.persistance.entity.Player;
import com.czavala.football_tournament_manager.persistance.repository.PlayerRepository;
import com.czavala.football_tournament_manager.service.PlayerService;
import com.czavala.football_tournament_manager.service.cloudinary.CloudinaryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final CloudinaryService cloudinaryService;

    public PlayerServiceImpl(PlayerRepository playerRepository, CloudinaryService cloudinaryService) {
        this.playerRepository = playerRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PlayerResponseDto> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable)
                .map(PlayerMapper::mapToPlayerResponseDto);
    }

    @Cacheable(value = "players", key = "#playerId")
    @Transactional(readOnly = true)
    @Override
    public PlayerResponseDto findById(Long playerId) {
        return PlayerMapper.mapToPlayerResponseDto(this.findPlayerEntityById(playerId));
    }

    @Override
    public CreatedPlayerDto create(SavePlayerDto playerDto) {

        // Valida si el número de camiseta del nuevo jugador ya existe
        validateSquadNumberExists(playerDto);

        // Crea instancia de nuevo jugador
        Player newPlayer = new Player();
        newPlayer.setFirstname(playerDto.getFirstname());
        newPlayer.setLastname(playerDto.getLastname());
        newPlayer.setRun(playerDto.getRunPlayer());
        newPlayer.setBirthDate(playerDto.getBirthDate());
        newPlayer.setNickname(playerDto.getNickname());
        newPlayer.setSquadNumber(playerDto.getSquadNumber());
        newPlayer.setActive(playerDto.getIsActive());
        newPlayer.setEnabled(playerDto.getIsEnabled());
        newPlayer.setTeamId(playerDto.getTeamId());

        // Sube imagen de player a Cloudinary retorna url de la imagen
        // Si request no contiene imagen, el campo url simplemente es null
        String imageUrl = cloudinaryService.uploadImageToCloudinary(
                playerDto.getImageFile(),
                "players", // Indica que se guardará en carpeta 'players' en Cloudinary
                playerDto.getFirstname() + "-" + playerDto.getLastname().toLowerCase() // Nombre que se asignará a imagen (nombre del jugador)
        );
        newPlayer.setImageUrl(imageUrl);
        newPlayer.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));

        playerRepository.save(newPlayer);
        return PlayerMapper.mapToCreatedPlayerDto(newPlayer);

    }

    @CachePut(value = "players", key = "#playerId")
    @Override
    public PlayerResponseDto updateById(Long playerId, SavePlayerDto playerDto) {

        try {

            // Obtiene desde db al jugador que se quiere actualizar
            Player playerFromDB = this.findPlayerEntityById(playerId);
            playerFromDB.setFirstname(playerDto.getFirstname());
            playerFromDB.setLastname(playerDto.getLastname());
            playerFromDB.setRun(playerDto.getRunPlayer());
            playerFromDB.setBirthDate(playerDto.getBirthDate());
            playerFromDB.setNickname(playerDto.getNickname());
            playerFromDB.setSquadNumber(playerDto.getSquadNumber());
            playerFromDB.setTeamId(playerDto.getTeamId());
            playerFromDB.setActive(playerDto.getIsActive());
            playerFromDB.setEnabled(playerDto.getIsEnabled());

            // Verifica si la petición contiene una nueva imagen
            // Si petición NO contiene una nueva imagen entonces se mantiene url de imagen existente
            if (playerDto.getImageFile() != null && !playerDto.getImageFile().isEmpty()) {

                // Sube nueva imagen a Cloudinary y obtiene nueva url
                String imageUrl = cloudinaryService.uploadImageToCloudinary(
                        playerDto.getImageFile(),
                        "players", // Indica que se guardará en carpeta 'players' en Cloudinary
                        playerDto.getFirstname().toLowerCase() + "-" + playerDto.getLastname().toLowerCase() // Nombre que se asignará a imagen (nombre del jugador)
                );

                // Actualiza nueva url de la imagen del team en db
                playerFromDB.setImageUrl(imageUrl);
            }
            playerFromDB.setLastModified(LocalDateTime.now(ZoneId.systemDefault()));

            // Guarda en db player actualizado
            playerRepository.save(playerFromDB);

            return PlayerMapper.mapToPlayerResponseDto(playerFromDB);

        } catch (DataIntegrityViolationException exception) {

            // Obtiene la causa raíz de la excepción
            Throwable rootCause = exception.getRootCause();

            // Verifica que la causa raíz de la excepción sea una violación de constraint
            if (rootCause instanceof SQLIntegrityConstraintViolationException sqlException) {

                // Obtiene mensaje de error desde db
                String sqlErrorMsg = sqlException.getMessage();

                // Verifica que el error sea una violación a unique constraint 'UK_PLAYER_SQUAD_TEAM'
                if (sqlErrorMsg != null && sqlErrorMsg.contains("UK_PLAYER_SQUAD_TEAM")) {

                    // Mensaje de error para el usuario
                    String errorMsg = "El número de camiseta " + playerDto.getSquadNumber() +
                            " ya está en uso. Por favor ingrese un número de camiseta válido.";

                    // Lanza excepción indicando que número de camiseta del jugador ya está en uso
                    throw new DuplicateSquadNumberException(errorMsg, sqlErrorMsg);
                }
            }

            // Lanza excepción genérica si causa raíz del error NO es una violación a una constraint
            throw exception;
        }
    }

    @CacheEvict(value = "players", key = "#playerId")
    @Override
    public void disableById(Long playerId) {
        Player playerFromDB = this.findPlayerEntityById(playerId);
        playerFromDB.setActive(false);
        playerRepository.save(playerFromDB);
    }

    @Transactional(readOnly = true)
    private Player findPlayerEntityById(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Player no encontrado. Player id: " + playerId));
    }

    // Método para validar si un número de camiseta ya está ocupado por algún jugador
    private void validateSquadNumberExists(SavePlayerDto playerDto) {
        boolean squadNumberExists = playerRepository.existsBySquadNumberAndTeamId(
                playerDto.getSquadNumber(), playerDto.getTeamId()
        );

        // Valida que el número de camiseta del nuevo jugador este disponible
        if (squadNumberExists) {
            throw new DuplicateSquadNumberException(
                    "El número de camiseta " + playerDto.getSquadNumber() +
                    " ya está en uso. Por favor ingrese un número de camiseta válido.");
        }
    }

}
