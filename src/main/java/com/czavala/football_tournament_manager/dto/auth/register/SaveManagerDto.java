package com.czavala.football_tournament_manager.dto.auth.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class SaveManagerDto implements Serializable {

    @NotBlank(message = "{generic.notblank}")
    @Size(max = 50, message = "{generic.size}")
    private String firstname;

    @NotBlank(message = "{generic.notblank}")
    @Size(max = 50, message = "{generic.size}")
    private String lastname;

    @NotBlank(message = "{generic.notblank}")
    @Size(min = 5, max = 30, message = "{generic.size}")
    @Pattern(regexp = "[a-zA-Z0-9-_]{8,30}", message = "{save-user-username.pattern}")
    private String username;

    @NotBlank(message = "{generic.notblank}")
    @Size(min = 10, max = 255, message = "{generic.size}")
    private String password;

    @JsonProperty("repeated_password")
    @NotBlank(message = "{generic.notblank}")
    @Size(min = 10, max = 255, message = "{generic.size}")
    private String repeatedPassword;

    @NotBlank(message = "{generic.notblank}")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "{save-user.email.pattern}")
    private String email;

    @NotBlank(message = "{generic.notblank}")
    @Size(min = 8, max = 9, message = "{generic.size}")
    @Pattern(regexp = "^\\d{8,9}[Kk]?$", message = "{save-user-run.pattern}")
    private String run;

    @JsonProperty("is_active")
    @NotNull(message = "{generic.notnull}")
    private Boolean isActive;

    @JsonProperty("user_role_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long userRoleId;

}
