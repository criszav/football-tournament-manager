package com.czavala.football_tournament_manager.dto.player;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerResponseDto {

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
    @JsonProperty("team_name") private String teamName;

}
