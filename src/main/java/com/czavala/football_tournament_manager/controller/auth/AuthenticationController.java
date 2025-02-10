package com.czavala.football_tournament_manager.controller.auth;

import com.czavala.football_tournament_manager.dto.auth.LogoutResponseDto;
import com.czavala.football_tournament_manager.dto.auth.login.LoginRequestDto;
import com.czavala.football_tournament_manager.dto.auth.login.LoginResponseDto;
import com.czavala.football_tournament_manager.dto.user.UserProfileDto;
import com.czavala.football_tournament_manager.service.auth.AuthenticationService;
import com.czavala.football_tournament_manager.service.auth.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;


@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequest) {
        LoginResponseDto auth = authenticationService.login(loginRequest);
        return ResponseEntity.ok(auth);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        boolean isTokenValid = jwtService.validateToken(token);
        return ResponseEntity.ok("Token is valid: " + isTokenValid);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> findMyProfile() {
        UserProfileDto userProfile = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(userProfile);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logout(HttpServletRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok(LogoutResponseDto.builder()
                        .message("Logout exitoso.")
                        .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                        .build());
    }
}
