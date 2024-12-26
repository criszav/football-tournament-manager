package com.czavala.football_tournament_manager.controller;

import com.czavala.football_tournament_manager.dto.user.SaveUserRoleDto;
import com.czavala.football_tournament_manager.dto.user.UserRoleResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.UserRole;
import com.czavala.football_tournament_manager.service.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user-role")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<Page<UserRoleResponseDto>> findAll(Pageable pageable) {
        Page<UserRoleResponseDto> userRoleList = userRoleService.findAll(pageable);
        return ResponseEntity.ok(userRoleList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleResponseDto> findOneById(@PathVariable Long id) {
        UserRoleResponseDto userRole = userRoleService.findById(id);
        return ResponseEntity.ok(userRole);
    }

    @PostMapping
    public ResponseEntity<UserRoleResponseDto> createOne(@RequestBody @Valid SaveUserRoleDto saveUserRole) {
        UserRoleResponseDto newUserRole = userRoleService.create(saveUserRole);
        return ResponseEntity.ok(newUserRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleResponseDto> updateOneById(@PathVariable Long id,
                                                             @RequestBody @Valid SaveUserRoleDto saveUserRole) {
        UserRoleResponseDto updatedUserRole = userRoleService.updateById(id, saveUserRole);
        return ResponseEntity.ok(updatedUserRole);
    }

}
