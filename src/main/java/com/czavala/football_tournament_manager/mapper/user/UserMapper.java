package com.czavala.football_tournament_manager.mapper.user;

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

}
