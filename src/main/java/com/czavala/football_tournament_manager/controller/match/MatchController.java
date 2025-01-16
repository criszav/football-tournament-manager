package com.czavala.football_tournament_manager.controller.match;

import com.czavala.football_tournament_manager.dto.match.MatchResponseDto;
import com.czavala.football_tournament_manager.dto.match.SaveMatchDto;
import com.czavala.football_tournament_manager.service.MatchService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<Page<MatchResponseDto>> findAllMatches(Pageable pageable) {
        Page<MatchResponseDto> matches = matchService.findAllMatches(pageable);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchResponseDto> findOneById(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchService.findOneById(matchId));
    }

    @PostMapping
    public ResponseEntity<MatchResponseDto> createOne(@RequestBody @Valid SaveMatchDto matchDto,
                                                      HttpServletRequest request) {
        MatchResponseDto newMatch = matchService.create(matchDto);

        String baseUrl = request.getRequestURL().toString();
        URI newMatchLocation = URI.create(baseUrl + "/" + newMatch.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(newMatchLocation)
                .body(newMatch);
    }

    @PutMapping("/{matchId}")
    public ResponseEntity<MatchResponseDto> updateOneById(@PathVariable Long matchId,
                                                          @RequestBody @Valid SaveMatchDto matchDto) {
        MatchResponseDto updatedMatch = matchService.updateById(matchId, matchDto);
        return ResponseEntity.ok(updatedMatch);
    }

    @GetMapping("/status/{matchStatusId}")
    public ResponseEntity<Page<MatchResponseDto>> findAllByMatchStatusId(@PathVariable Long matchStatusId,
                                                                         Pageable pageable) {
        Page<MatchResponseDto> matchesByStatus = matchService.findMatchesByMatchStatusBy(matchStatusId, pageable);
        return ResponseEntity.ok(matchesByStatus);
    }

}
