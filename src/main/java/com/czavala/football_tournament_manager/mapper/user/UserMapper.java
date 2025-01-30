package com.czavala.football_tournament_manager.mapper.user;

import com.czavala.football_tournament_manager.config.security.CustomUserDetails;
import com.czavala.football_tournament_manager.dto.auth.register.RegisteredManagerDto;
import com.czavala.football_tournament_manager.dto.user.UserProfileDto;
import com.czavala.football_tournament_manager.dto.user.ResponseUserTournamentDto;
import com.czavala.football_tournament_manager.dto.user.UserResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.User;

public class UserMapper {

    public static ResponseUserTournamentDto mapToUserTournamentDto(User user) {

        if (user == null) return null;

        return ResponseUserTournamentDto
                .builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .email(user.getEmail())
                .roleId(user.getUserRoleId())
                .isActive(user.isActive())
                .build();
    }


    public static UserResponseDto mapToUserResponseDto(User userEntity) {

        if (userEntity == null) return null;

        return UserResponseDto.builder()
                .id(userEntity.getId())
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .run(userEntity.getRun())
                .userRole(userEntity.getUserRole().getRoleName())
                .isActive(userEntity.isActive())
                .build();
    }

    public static RegisteredManagerDto mapToRegisteredManagerDto(User userEntity, String token) {

        if (userEntity == null) return null;

        return RegisteredManagerDto.builder()
                .id(userEntity.getId())
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .role(userEntity.getUserRole().getRoleName())
                .jwt(token)
                .build();
    }

    public static UserProfileDto mapToUserProfileDto(CustomUserDetails userDetails) {

        if (userDetails == null) return null;

        return UserProfileDto.builder()
                .firstname(userDetails.getUser().getFirstname())
                .lastname(userDetails.getUser().getLastname())
                .username(userDetails.getUsername())
                .email(userDetails.getUser().getEmail())
                .run(userDetails.getUser().getRun())
                .role(userDetails.getUser().getUserRole().getRoleName())
                .isActive(userDetails.getUser().isActive())
                .createdAt(userDetails.getUser().getCreatedAt())
                .lastModified(userDetails.getUser().getLastModified())
                .isEnabled(userDetails.isEnabled())
                .build();

    }
}
