package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.player.PlayerResponseDto;
import com.czavala.football_tournament_manager.dto.player.SavePlayerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    Page<PlayerResponseDto> findAll(Pageable pageable);

    PlayerResponseDto findById(Long playerId);

    PlayerResponseDto create(SavePlayerDto playerDto);

    PlayerResponseDto updateById(Long playerId, SavePlayerDto playerDto);

    void disableById(Long playerId);
}
