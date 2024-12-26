package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.user.SaveUserRoleDto;
import com.czavala.football_tournament_manager.dto.user.UserRoleResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRoleService {
    
    Page<UserRoleResponseDto> findAll(Pageable pageable);

    UserRoleResponseDto findById(Long id);

    UserRoleResponseDto create(SaveUserRoleDto saveUserRole);

    UserRoleResponseDto updateById(Long id, SaveUserRoleDto saveUserRole);

    UserRole findUserRoleEntityById(Long id);
}
