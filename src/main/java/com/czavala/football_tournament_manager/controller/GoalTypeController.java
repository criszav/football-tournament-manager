package com.czavala.football_tournament_manager.controller;

import com.czavala.football_tournament_manager.dto.goal.GoalTypeResponseDto;
import com.czavala.football_tournament_manager.dto.goal.SaveGoalTypeDto;
import com.czavala.football_tournament_manager.persistance.entity.GoalType;
import com.czavala.football_tournament_manager.service.GoalTypeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/goal-type")
public class GoalTypeController {

    private final GoalTypeService goalTypeService;

    public GoalTypeController(GoalTypeService goalTypeService) {
        this.goalTypeService = goalTypeService;
    }

    @GetMapping
    public ResponseEntity<Page<GoalTypeResponseDto>> findAll(Pageable pageable) {
        Page<GoalTypeResponseDto> goalTypes = goalTypeService.findAll(pageable);
        return ResponseEntity.ok(goalTypes);
    }

    @GetMapping("/{goal-type-id}")
    public ResponseEntity<GoalTypeResponseDto> findOneById(@PathVariable(name = "goal-type-id") Long goalTypeId) {
        GoalTypeResponseDto goalType = goalTypeService.findById(goalTypeId);
        return ResponseEntity.ok(goalType);
    }

    @PostMapping
    public ResponseEntity<GoalTypeResponseDto> createOne(@RequestBody @Valid SaveGoalTypeDto saveGoalTypeDto) {
        GoalTypeResponseDto newGoalType = goalTypeService.create(saveGoalTypeDto);
        return ResponseEntity.ok(newGoalType);
    }

    @PutMapping("/{goal-type-id}")
    public ResponseEntity<GoalTypeResponseDto> updateOneById(@PathVariable(name = "goal-type-id") Long goalTypeId,
                                                  @RequestBody @Valid SaveGoalTypeDto saveGoalTypeDto) {

        GoalTypeResponseDto updatedGoalType = goalTypeService.updateById(goalTypeId, saveGoalTypeDto);
        return ResponseEntity.ok(updatedGoalType);
    }

    @PutMapping("/{goal-type-id}/disable")
    public ResponseEntity<Void> disableOneById(@PathVariable(name = "goal-type-id") Long goalTypeId) {
        goalTypeService.disableById(goalTypeId);
        return ResponseEntity.noContent().build();
    }

}
