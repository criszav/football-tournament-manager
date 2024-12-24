package com.czavala.football_tournament_manager.dto.goal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalTypeResponseDto {

    private Long id;
    @JsonProperty(value = "type_name") private String typeName;
    private String description;
    @JsonProperty(value = "is_active") private boolean isActive;


}
