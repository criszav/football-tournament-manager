package com.czavala.football_tournament_manager.controller.match;

import com.czavala.football_tournament_manager.dto.match.MatchStatusResponseDto;
import com.czavala.football_tournament_manager.dto.match.SaveMatchStatusDto;
import com.czavala.football_tournament_manager.service.MatchStatusService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/match-status")
public class MatchStatusController {

    private final MatchStatusService matchStatusService;

    public MatchStatusController(MatchStatusService matchStatusService) {
        this.matchStatusService = matchStatusService;
    }

    @GetMapping
    public ResponseEntity<Page<MatchStatusResponseDto>> findAll(Pageable pageable) {
        Page<MatchStatusResponseDto> matchStatusList = matchStatusService.findAll(pageable);
        return ResponseEntity.ok(matchStatusList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchStatusResponseDto> findOneById(@PathVariable Long id) {
        MatchStatusResponseDto matchStatus = matchStatusService.findById(id);
        return ResponseEntity.ok(matchStatus);
    }

    @PostMapping
    public ResponseEntity<MatchStatusResponseDto> createOne(@RequestBody @Valid SaveMatchStatusDto matchStatusDto,
                                                            HttpServletRequest request) {

        MatchStatusResponseDto newMatchStatus = matchStatusService.create(matchStatusDto);

        // Agrega Header 'Location' con URI del nuevo recurso creado
        String baseUrl = request.getRequestURL().toString();
        URI newMatchStatusLocation = URI.create(baseUrl + "/" + newMatchStatus.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(newMatchStatusLocation)
                .body(newMatchStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchStatusResponseDto> updateOneById(@PathVariable Long id, @RequestBody @Valid SaveMatchStatusDto saveMatchStatus) {
        MatchStatusResponseDto updatedMatchStatus = matchStatusService.updateById(id, saveMatchStatus);
        return ResponseEntity.ok(updatedMatchStatus);
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<Void> disableOneById(@PathVariable Long id) {
        matchStatusService.disableById(id);
        return ResponseEntity.noContent().build();
    }

}
