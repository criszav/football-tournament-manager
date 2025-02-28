package com.czavala.football_tournament_manager.mapper.player;

import com.czavala.football_tournament_manager.dto.player.CreatedPlayerDto;
import com.czavala.football_tournament_manager.dto.player.PlayerResponseDto;
import com.czavala.football_tournament_manager.dto.player.PlayerTeamDto;
import com.czavala.football_tournament_manager.dto.player.SavePlayerDto;
import com.czavala.football_tournament_manager.persistance.entity.Player;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PlayerMapper implements Serializable {

    public static PlayerTeamDto mapToPlayerTeamDto(Player playerEntity) {

        if (playerEntity == null) return null;

        return PlayerTeamDto.builder()
                .firstname(playerEntity.getFirstname())
                .lastname(playerEntity.getLastname())
                .squadNumber(playerEntity.getSquadNumber())
                .isActive(playerEntity.isActive())
                .isEnabled(playerEntity.isEnabled())
                .build();
    }

    public static PlayerResponseDto mapToPlayerResponseDto(Player playerEntity) {

        if (playerEntity == null) return null;

        return PlayerResponseDto.builder()
                .id(playerEntity.getId())
                .firstname(playerEntity.getFirstname())
                .lastname(playerEntity.getLastname())
                .nickname(playerEntity.getNickname())
                .imageUrl(playerEntity.getImageUrl())
                .squadNumber(playerEntity.getSquadNumber())
                .isActive(playerEntity.isActive())
                .isEnabled(playerEntity.isEnabled())
                .teamName(playerEntity.getTeam().getName())
                .build();
    }

    public static CreatedPlayerDto mapToCreatedPlayerDto(Player playerEntity) {

        if (playerEntity == null) return null;

        return CreatedPlayerDto.builder()
                .id(playerEntity.getId())
                .firstname(playerEntity.getFirstname())
                .lastname(playerEntity.getLastname())
                .nickname(playerEntity.getNickname())
                .imageUrl(playerEntity.getImageUrl())
                .squadNumber(playerEntity.getSquadNumber())
                .isActive(playerEntity.isActive())
                .isEnabled(playerEntity.isEnabled())
                .teamId(playerEntity.getTeamId())
                .build();
    }

}
