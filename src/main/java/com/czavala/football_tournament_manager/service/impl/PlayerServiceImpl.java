package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.player.PlayerResponseDto;
import com.czavala.football_tournament_manager.dto.player.SavePlayerDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.player.PlayerMapper;
import com.czavala.football_tournament_manager.persistance.entity.Player;
import com.czavala.football_tournament_manager.persistance.repository.PlayerRepository;
import com.czavala.football_tournament_manager.service.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PlayerResponseDto> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable)
                .map(PlayerMapper::mapToPlayerResponseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public PlayerResponseDto findById(Long playerId) {
        return PlayerMapper.mapToPlayerResponseDto(this.findPlayerEntityById(playerId));
    }

    @Override
    public PlayerResponseDto create(SavePlayerDto playerDto) {
        Player player = PlayerMapper.mapToPlayerEntity(playerDto);
        playerRepository.save(player);
        return PlayerMapper.mapToPlayerResponseDto(player);
    }

    @Override
    public PlayerResponseDto updateById(Long playerId, SavePlayerDto playerDto) {
        Player playerFromDB = this.findPlayerEntityById(playerId);
        playerFromDB.setFirstname(playerDto.getFirstname());
        playerFromDB.setLastname(playerDto.getLastname());
        playerFromDB.setNickname(playerDto.getNickname());
        playerFromDB.setSquadNumber(playerDto.getSquadNumber());
        playerFromDB.setImageUrl(playerDto.getImageUrl());
        playerFromDB.setTeamId(playerDto.getTeamId());
        playerFromDB.setActive(playerDto.getIsActive());
        playerFromDB.setEnabled(playerDto.getIsEnabled());
        playerFromDB.setLastModified(LocalDateTime.now(ZoneId.systemDefault()));
        playerRepository.save(playerFromDB);

        return PlayerMapper.mapToPlayerResponseDto(playerFromDB);
    }

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
}
