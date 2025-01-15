package com.czavala.football_tournament_manager.controller.goal;

import com.czavala.football_tournament_manager.dto.goal.CreatedGoalResponseDto;
import com.czavala.football_tournament_manager.dto.goal.GetGoalResponseDto;
import com.czavala.football_tournament_manager.dto.goal.SaveGoalDto;
import com.czavala.football_tournament_manager.service.GoalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public ResponseEntity<Page<GetGoalResponseDto>> findAllGoals(Pageable pageable) {
        Page<GetGoalResponseDto> goals = goalService.findAll(pageable);

        return ResponseEntity.ok(goals);
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<GetGoalResponseDto> findOneById(@PathVariable Long goalId) {
        return ResponseEntity.ok(goalService.findOneById(goalId));
    }

    @PostMapping
    public ResponseEntity<CreatedGoalResponseDto> createOne(@RequestBody @Valid SaveGoalDto saveGoalDto,
                                                            HttpServletRequest request) {

        CreatedGoalResponseDto newGoal = goalService.createOne(saveGoalDto);

        String baseUrl = request.getRequestURL().toString();
        URI newGoalLocation = URI.create(baseUrl + "/" + newGoal.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(newGoalLocation)
                .body(newGoal);
    }

    @PutMapping("/{goalId}")
    public ResponseEntity<GetGoalResponseDto> updateOneById(@PathVariable Long goalId,
                                                            @RequestBody @Valid SaveGoalDto saveGoalDto) {
        GetGoalResponseDto updatedGoal = goalService.updateById(goalId, saveGoalDto);
        return ResponseEntity.ok(updatedGoal);
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<Page<GetGoalResponseDto>> findAllGoalsByPlayerId(@PathVariable Long playerId,
                                                                           Pageable pageable) {
        Page<GetGoalResponseDto> goalsByPlayer = goalService.findGoalsByPlayerId(playerId, pageable);
        return ResponseEntity.ok(goalsByPlayer);
    }

    @GetMapping("/player/{playerId}/team/{teamId}")
    public ResponseEntity<Page<GetGoalResponseDto>> findPlayerGoalsByTeamId(@PathVariable Long playerId,
                                                                            @PathVariable Long teamId,
                                                                            Pageable pageable) {
        Page<GetGoalResponseDto> goals = goalService.findPlayerGoalsByTeamId(playerId, teamId, pageable);
        return ResponseEntity.ok(goals);
    }

}
