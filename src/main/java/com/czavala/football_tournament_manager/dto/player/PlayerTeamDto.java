package com.czavala.football_tournament_manager.dto.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PlayerTeamDto implements Serializable {

    private String firstname;

    private String lastname;

    @JsonProperty(value = "squad_number")
    private Integer squadNumber;

    @JsonProperty(value = "is_active")
    private Boolean isActive;

    @JsonProperty(value = "is_enabled")
    private Boolean isEnabled;

}
