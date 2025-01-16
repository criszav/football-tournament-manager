package com.czavala.football_tournament_manager.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDto {

    @JsonProperty("user_id")
    private Long id;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String run;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("user_role")
    private String userRole;

}
