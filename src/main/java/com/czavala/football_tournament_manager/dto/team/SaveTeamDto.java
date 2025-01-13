package com.czavala.football_tournament_manager.dto.team;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SaveTeamDto implements Serializable {

    @JsonProperty(value = "team_name")
    @NotBlank
    @Size(max = 255)
    private String name;

    @JsonProperty(value = "team_code")
    @NotBlank
    @Size(min = 2, max = 3)
    private String teamCode;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty(value = "is_active")
    @NotNull
    private Boolean isActive;

    @JsonProperty(value = "is_enabled")
    @NotNull
    private Boolean isEnabled;

    @JsonProperty(value = "user_id")
    @NotNull
    @Positive
    private Long userId;
}
