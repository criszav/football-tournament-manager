package com.czavala.football_tournament_manager.service.auth;

import com.czavala.football_tournament_manager.config.security.CustomUserDetails;
import com.czavala.football_tournament_manager.dto.auth.login.LoginRequestDto;
import com.czavala.football_tournament_manager.dto.auth.login.LoginResponseDto;
import com.czavala.football_tournament_manager.dto.auth.register.RegisteredManagerDto;
import com.czavala.football_tournament_manager.dto.auth.register.SaveManagerDto;
import com.czavala.football_tournament_manager.dto.user.UserProfileDto;
import com.czavala.football_tournament_manager.mapper.user.UserMapper;
import com.czavala.football_tournament_manager.persistance.entity.User;
import com.czavala.football_tournament_manager.service.UserService;
import com.czavala.football_tournament_manager.service.security.CustomUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.rmi.AccessException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService,
                                 JwtService jwtService,
                                 CustomUserDetailsService userDetailsService,
                                 AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public RegisteredManagerDto registerManager(SaveManagerDto saveManagerDto) {
        // Registra user en db
        User user = userService.registerOneManager(saveManagerDto);

        // Carga detalles del user registrado previamente para generar token
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService
                .loadUserByUsername(user.getUsername());

        // Asigna claims extra (opcionales) al token
        Map<String,Object> extraClaims = generateExtraClaims(userDetails);

        // Genera token
        String token = jwtService.generateToken(userDetails, extraClaims);

        return UserMapper.mapToRegisteredManagerDto(user, token);
    }

    // Genera claims extras para asignarlos el token (estos son opcionales)
    private Map<String, Object> generateExtraClaims(CustomUserDetails userDetails) {

        String name = userDetails.getUser().getFirstname() + " " + userDetails.getUser().getLastname();
        String role = userDetails.getUser().getUserRole().getRoleName();

        // Estos claims extra son de prueba, se puede agregar/quitar según se requiera
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("id", userDetails.getUser().getId());
        extraClaims.put("name", name);
        extraClaims.put("email", userDetails.getUser().getEmail());
        extraClaims.put("role", role);

        return extraClaims;
    }

    public LoginResponseDto login(LoginRequestDto loginRequest) {

        // Crea un token de Authentication que representa un username y password (del user que esta intentando ingresar)
        // para ser autenticado por el AuthenticationManager
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        // Realiza la autenticación como tal del token que contiene username y password de user que intenta ingresar
        authenticationManager.authenticate(authenticationToken);

        // Obtiene datos del user luego de haber sido autenticado exitosamente
        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(loginRequest.getUsername());

        // Genera token para user autenticado exitosamente
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        // Envía respuesta con token
        return LoginResponseDto.builder()
                .jwt(jwt)
                .issuedAt(LocalDateTime.now(ZoneId.systemDefault()))
                .build();
    }

    public UserProfileDto findLoggedInUser() throws AccessException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessException("Error, usuario no autenticado");
        }

        // Obtiene username (principal) desde el objeto "Authentication" del user que ha iniciado sesion
        String username = (String) authentication.getPrincipal();

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        return UserMapper.mapToUserProfileDto(customUserDetails);
    }
}
