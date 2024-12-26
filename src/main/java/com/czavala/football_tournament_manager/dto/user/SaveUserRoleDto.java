package com.czavala.football_tournament_manager.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SaveUserRoleDto implements Serializable {

    @JsonProperty(value = "role_name")
    @NotBlank
    @Size(max = 50)
    private String roleName;

    @Size(max = 255)
    private String description;
}
