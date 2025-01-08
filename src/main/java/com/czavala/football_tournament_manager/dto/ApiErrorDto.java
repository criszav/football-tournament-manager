package com.czavala.football_tournament_manager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApiErrorDto implements Serializable {

    @JsonProperty(value = "http_code")
    private Integer httpCode;

    @JsonProperty(value = "http_method")
    private String httpMethod;

    private String url;

    private String message;

    @JsonProperty(value = "backend_message")
    private String backendMessage;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp;

    private List<String> details;

}
