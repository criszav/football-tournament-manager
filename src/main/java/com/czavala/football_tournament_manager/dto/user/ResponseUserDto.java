package com.czavala.football_tournament_manager.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseUserDto {

    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    @JsonProperty(value = "is_active")
    private Boolean isActive;

    @JsonProperty(value = "role_id")
    private Long roleId;
}
