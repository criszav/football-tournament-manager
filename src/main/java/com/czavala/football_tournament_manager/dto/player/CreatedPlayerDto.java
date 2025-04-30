package com.czavala.football_tournament_manager.dto.player;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CreatedPlayerDto {

    @JsonProperty("player_id") private Long id;
    private String firstname;
    private String lastname;
    @JsonProperty("run_player") private String runPlayer;
    @JsonProperty(value = "birth_date") @JsonFormat(pattern = "dd/MM/yyyy") private Date birthDate;
    private String nickname;
    @JsonProperty("squad_number") private Integer squadNumber;
    @JsonProperty("image_url") private String imageUrl;
    @JsonProperty("is_active") private Boolean isActive;
    @JsonProperty("is_enabled") private Boolean isEnabled;
    @JsonProperty("team_id") private Long teamId;

}
