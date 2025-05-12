package com.czavala.football_tournament_manager.dto.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamResponseDto implements Serializable {

    private Long id;

    @JsonProperty(value = "team_name")
    private String teamName;

    @JsonProperty(value = "team_code")
    private String teamCode;

    @JsonProperty(value = "image_url")
    private String imageUrl;

    @JsonProperty(value = "is_active")
    private Boolean isActive;

}
