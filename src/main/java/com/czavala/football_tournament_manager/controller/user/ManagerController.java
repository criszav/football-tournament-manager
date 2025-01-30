package com.czavala.football_tournament_manager.controller.user;

import com.czavala.football_tournament_manager.dto.auth.register.RegisteredManagerDto;
import com.czavala.football_tournament_manager.dto.auth.register.SaveManagerDto;
import com.czavala.football_tournament_manager.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final AuthenticationService authenticationService;

    public ManagerController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // MANAGERS NO SE PUEDEN REGISTRAR ELLOS SOLOS, ADMIN ES QUIEN DEBE CREARLES LA CUENTA
    // Sólo el ADMIN tendrá acceso a estos endpoints para registrar usuarios de tipo manager
    @PostMapping("/register")
    public ResponseEntity<RegisteredManagerDto> registerManager(@RequestBody @Valid SaveManagerDto saveManagerDto) {
        RegisteredManagerDto newManager = authenticationService.registerManager(saveManagerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newManager);
    }

}
