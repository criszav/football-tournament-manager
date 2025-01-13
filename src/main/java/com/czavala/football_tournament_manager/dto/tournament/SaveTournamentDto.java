package com.czavala.football_tournament_manager.dto.tournament;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SaveTournamentDto implements Serializable {

    @NotBlank
    @Size(min = 4, max = 255)
    private String name;

    @JsonProperty(value = "start_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "{generic.notnull}")
    private Date startDate;

    @JsonProperty(value = "end_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Future(message = "{generic.future}")
    private Date endDate;

    @JsonProperty(value = "number_of_teams")
    @Min(value = 1, message = "{generic.min}")
    @Max(value = 80, message = "{generic.max}")
    private Integer numberOfTeams;

    @JsonProperty(value = "is_active")
    @NotNull(message = "{generic.notnull}")
    private Boolean isActive;

    @JsonProperty(value = "user_id")
    @Positive(message = "{generic.positive}")
    private Long userId;

    @JsonProperty(value = "tournament_status_id")
    @Positive(message = "{generic.positive}")
    private Long tournamentStatusId;

}
