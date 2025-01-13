package com.czavala.football_tournament_manager.mapper.player;

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

    public static Player mapToPlayerEntity(SavePlayerDto savePlayerDto) {

        if (savePlayerDto == null) return null;

        Player player = new Player();
        player.setFirstname(savePlayerDto.getFirstname());
        player.setLastname(savePlayerDto.getLastname());
        player.setNickname(savePlayerDto.getNickname());
        player.setImageUrl(savePlayerDto.getImageUrl());
        player.setSquadNumber(savePlayerDto.getSquadNumber());
        player.setActive(savePlayerDto.getIsActive());
        player.setEnabled(savePlayerDto.getIsEnabled());
        player.setTeamId(savePlayerDto.getTeamId());

        return player;
    }

}
