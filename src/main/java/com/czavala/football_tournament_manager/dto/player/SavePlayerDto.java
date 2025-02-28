package com.czavala.football_tournament_manager.dto.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public class SavePlayerDto implements Serializable {

    @NotBlank(message = "{generic.notblank")
    @Size(max = 255, message = "{generic.size}")
    private String firstname;

    @NotBlank(message = "{generic.notblank")
    @Size(max = 255, message = "{generic.size}")
    private String lastname;

    @Size(max = 40, message = "{generic.size}")
    private String nickname;

    @JsonProperty(value = "image_file")
    private MultipartFile imageFile;

    @JsonProperty(value = "squad_number")
    @Positive(message = "{generic.positive}")
    @Max(value = 99, message = "{generic.max}")
    @Min(value = 1, message = "{generic.min}")
    private Integer squadNumber;

    @JsonProperty(value = "is_active")
    @NotNull(message = "{generic.notnull}")
    private Boolean isActive;

    @JsonProperty(value = "is_enabled")
    @NotNull(message = "{generic.notnull}")
    private Boolean isEnabled;

    @JsonProperty(value = "team_id")
    @Positive(message = "{generic.positive}")
    private Long teamId;
}
