package com.czavala.football_tournament_manager.dto.goal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SaveGoalTypeDto implements Serializable {

    @JsonProperty(value = "type_name")
    @NotBlank(message = "{generic.notblank}")
    @Size(max = 50, message = "{generic.size}")
    private String typeName;

    @Size(max = 255, message = "{generic.size}")
    private String description;

    @JsonProperty(value = "is_active")
    @NotNull(message = "{generic.notnull}")
    private Boolean isActive;
}
