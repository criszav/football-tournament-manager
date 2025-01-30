package com.czavala.football_tournament_manager.dto.auth.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class RegisteredManagerDto implements Serializable {

    @JsonProperty("manager_id")
    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String role;

    private String jwt;

}
