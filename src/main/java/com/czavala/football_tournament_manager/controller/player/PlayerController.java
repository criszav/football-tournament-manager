package com.czavala.football_tournament_manager.controller.player;

import com.czavala.football_tournament_manager.dto.player.CreatedPlayerDto;
import com.czavala.football_tournament_manager.dto.player.PlayerResponseDto;
import com.czavala.football_tournament_manager.dto.player.SavePlayerDto;
import com.czavala.football_tournament_manager.service.PlayerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<Page<PlayerResponseDto>> findAllPlayers(Pageable pageable) {
        Page<PlayerResponseDto> players = playerService.findAll(pageable);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> findOneById(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerService.findById(playerId));
    }

    @PostMapping
    public ResponseEntity<CreatedPlayerDto> createOne(
            @RequestPart("player_data") @Valid SavePlayerDto playerDto,
            @RequestPart(value = "image_file", required = false) MultipartFile imageFile,
            HttpServletRequest request) {

        playerDto.setImageFile(imageFile);
        CreatedPlayerDto newPlayer = playerService.create(playerDto);

        String baseUrl = request.getRequestURL().toString();
        URI newPlayerLocation = URI.create(baseUrl + "/" + newPlayer.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(newPlayerLocation)
                .body(newPlayer);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerResponseDto> updateOneById(
            @PathVariable Long playerId,
            @RequestPart("player_data") @Valid SavePlayerDto playerDto,
            @RequestPart(value = "image_file", required = false) MultipartFile imageFile) {

        playerDto.setImageFile(imageFile);
        PlayerResponseDto updatedPlayer = playerService.updateById(playerId, playerDto);

        return ResponseEntity.ok(updatedPlayer);
    }

    @PutMapping("/{playerId}/disable")
    public ResponseEntity<Void> disableOneById(@PathVariable Long playerId) {
        playerService.disableById(playerId);
        return ResponseEntity.noContent().build();
    }

}
