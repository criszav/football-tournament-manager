package com.czavala.football_tournament_manager.dto.tournament;

import com.czavala.football_tournament_manager.dto.user.ResponseUserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
public class TournamentResponseDto implements Serializable {

    private Long id;

    private String name;

    @JsonProperty(value = "start_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @JsonProperty(value = "end_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @JsonProperty(value = "number_of_teams")
    private Integer numberOfTeams;

    @JsonProperty(value = "is_active")
    private Boolean isActive;

    @JsonProperty(value = "tournament_status")
    private TournamentStatusResponseDto tournamentStatus;

    @JsonProperty(value = "created_by")
    private ResponseUserDto user;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty(value = "last_modified")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime lastModified;

}
