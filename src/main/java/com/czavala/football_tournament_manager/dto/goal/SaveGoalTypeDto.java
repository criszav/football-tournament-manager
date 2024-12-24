package com.czavala.football_tournament_manager.dto.goal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveGoalTypeDto {

    @JsonProperty(value = "type_name")
    @NotBlank
    @Size(min = 3, max = 255)
    private String typeName;

    @NotBlank
    @Size(min = 5, max = 255)
    private String description;

    @JsonProperty(value = "is_active")
    @NotNull
    private boolean isActive;
}
