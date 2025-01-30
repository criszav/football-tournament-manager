package com.czavala.football_tournament_manager.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Builder
public class UserProfileDto implements Serializable {

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String run;

    @JsonProperty("is_active")
    private Boolean isActive;

    private String role;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonProperty("last_modified")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime lastModified;

    @JsonProperty("account_enabled")
    private Boolean isEnabled;

}
