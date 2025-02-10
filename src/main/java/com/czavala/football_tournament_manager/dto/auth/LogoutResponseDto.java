package com.czavala.football_tournament_manager.dto.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class LogoutResponseDto implements Serializable {

    private String message;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") private LocalDateTime timestamp;

}
