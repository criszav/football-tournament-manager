package com.czavala.football_tournament_manager.mapper.user;

import com.czavala.football_tournament_manager.dto.user.SaveUserRoleDto;
import com.czavala.football_tournament_manager.dto.user.UserRoleResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.UserRole;

public class UserRoleMapper {

    public static UserRoleResponseDto mapToUserRoleResponseDto(UserRole userRole) {

        if (userRole == null) return null;

        return UserRoleResponseDto.builder()
                .id(userRole.getId())
                .roleName(userRole.getRoleName())
                .description(userRole.getDescription())
                .build();
    }

    public static UserRole mapToUserRoleEntity(SaveUserRoleDto saveUserRole) {

        if (saveUserRole == null) return null;

        UserRole userRole = new UserRole();
        userRole.setRoleName(saveUserRole.getRoleName());
        userRole.setDescription(saveUserRole.getDescription());

        return userRole;
    }

}
