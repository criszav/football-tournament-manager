package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.user.SaveUserRoleDto;
import com.czavala.football_tournament_manager.dto.user.UserRoleResponseDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.user.UserRoleMapper;
import com.czavala.football_tournament_manager.persistance.entity.UserRole;
import com.czavala.football_tournament_manager.persistance.repository.UserRoleRepository;
import com.czavala.football_tournament_manager.service.UserRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UserRoleResponseDto> findAll(Pageable pageable) {
        return userRoleRepository.findAll(pageable)
                .map(UserRoleMapper::mapToUserRoleResponseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public UserRoleResponseDto findById(Long id) {
        UserRole userRole = this.findUserRoleEntityById(id);
        return UserRoleMapper.mapToUserRoleResponseDto(userRole);
    }

    @Override
    public UserRoleResponseDto create(SaveUserRoleDto saveUserRole) {
        UserRole userRole = UserRoleMapper.mapToUserRoleEntity(saveUserRole);
        userRoleRepository.save(userRole);

        return UserRoleMapper.mapToUserRoleResponseDto(userRole);
    }

    @Override
    public UserRoleResponseDto updateById(Long id, SaveUserRoleDto saveUserRole) {
        UserRole userRoleFromDB = this.findUserRoleEntityById(id);
        userRoleFromDB.setRoleName(saveUserRole.getRoleName());
        userRoleFromDB.setDescription(saveUserRole.getDescription());
        userRoleRepository.save(userRoleFromDB);

        return UserRoleMapper.mapToUserRoleResponseDto(userRoleFromDB);
    }

    @Transactional(readOnly = true)
    @Override
    public UserRole findUserRoleEntityById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Role con ID " + id + " no encontrado"));
    }
}
