package com.czavala.football_tournament_manager.dto.tournament;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class TournamentStatusResponseDto implements Serializable {

    private Long id;

    @JsonProperty(value = "status_name")
    private String statusName;

    private String description;

    @JsonProperty(value = "is_active")
    private boolean isActive;
}
