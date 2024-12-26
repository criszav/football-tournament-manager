package com.czavala.football_tournament_manager.dto.tournament;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SaveTournamentStatusDto implements Serializable {

    @JsonProperty(value = "status_name")
    @NotBlank
    @Size(max = 50)
    private String statusName;

    @Size(max = 255)
    private String description;

    @JsonProperty(value = "is_active")
    @NotNull
    private Boolean isActive;
}
