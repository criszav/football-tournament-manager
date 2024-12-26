package com.czavala.football_tournament_manager.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserRoleResponseDto implements Serializable {

    private Long id;

    @JsonProperty(value = "role_name")
    private String roleName;

    private String description;
}
