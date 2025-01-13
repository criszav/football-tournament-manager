package com.czavala.football_tournament_manager.dto.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SavePlayerDto implements Serializable {

    @NotBlank
    @Size(max = 255)
    private String firstname;

    @NotBlank
    @Size(max = 255)
    private String lastname;

    @Size(max = 40)
    private String nickname;

    @JsonProperty(value = "image_url")
    private String imageUrl;

    @JsonProperty(value = "squad_number")
    @Positive
    @Max(value = 99)
    private Integer squadNumber;

    @JsonProperty(value = "is_active")
    @NotNull
    private Boolean isActive;

    @JsonProperty(value = "is_enabled")
    @NotNull
    private Boolean isEnabled;

    @JsonProperty(value = "team_id")
    @Positive
    private Long teamId;
}
