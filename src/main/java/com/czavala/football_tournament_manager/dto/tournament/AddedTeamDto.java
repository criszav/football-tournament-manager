package com.czavala.football_tournament_manager.dto.tournament;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AddedTeamDto implements Serializable {

    @JsonProperty("tournament_id")
    private Long tournamentId;

    @JsonProperty("team_id")
    private Long teamId;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd-MM-yyyy hh:MM:ss")
    private LocalDateTime createdAt;

}
