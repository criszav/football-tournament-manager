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
    @NotBlank(message = "{generic.notblank}")
    @Size(max = 50, message = "{generic.max}")
    private String name;

    @JsonProperty(value = "team_code")
    @NotBlank(message = "{generic.notblank}")
    @Size(min = 2, max = 3, message = "{generic.size}")
    private String teamCode;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty(value = "is_active")
    @NotNull(message = "{generic.notnull}")
    private Boolean isActive;

    @JsonProperty(value = "is_enabled")
    @NotNull(message = "{generic.notnull}")
    private Boolean isEnabled;

    @JsonProperty(value = "user_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long userId;
}
