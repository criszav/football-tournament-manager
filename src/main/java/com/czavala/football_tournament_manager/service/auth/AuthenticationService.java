package com.czavala.football_tournament_manager.service.auth;

import com.czavala.football_tournament_manager.config.security.CustomUserDetails;
import com.czavala.football_tournament_manager.dto.auth.login.LoginRequestDto;
import com.czavala.football_tournament_manager.dto.auth.login.LoginResponseDto;
import com.czavala.football_tournament_manager.dto.auth.register.RegisteredManagerDto;
import com.czavala.football_tournament_manager.dto.auth.register.SaveManagerDto;
import com.czavala.football_tournament_manager.dto.user.UserProfileDto;
import com.czavala.football_tournament_manager.mapper.user.UserMapper;
import com.czavala.football_tournament_manager.persistance.entity.TokenJwt;
import com.czavala.football_tournament_manager.persistance.entity.User;
import com.czavala.football_tournament_manager.persistance.repository.TokenJwtRepository;
import com.czavala.football_tournament_manager.service.UserService;
import com.czavala.football_tournament_manager.service.security.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final TokenJwtRepository jwtRepository;

    public AuthenticationService(UserService userService,
                                 JwtService jwtService,
                                 CustomUserDetailsService userDetailsService,
                                 AuthenticationManager authenticationManager,
                                 TokenJwtRepository jwtRepository) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtRepository = jwtRepository;
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
        // Guarda token en db
        saveUserToken(user, jwt);

        // Envía respuesta con token
        return LoginResponseDto.builder()
                .jwt(jwt)
                .issuedAt(LocalDateTime.now(ZoneId.systemDefault()))
                .build();
    }

    private void saveUserToken(CustomUserDetails userDetails, String jwt) {

        TokenJwt token = new TokenJwt();
        token.setToken(jwt);
        token.setUser(userDetails.getUser());
        token.setExpiration(jwtService.extractExpirationDate(jwt));
        token.setValid(true);

        jwtRepository.save(token);
    }

    public UserProfileDto findLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException("Error, usuario no autenticado");
        }

        // Obtiene username (principal) desde el objeto "Authentication" del user que ha iniciado sesion
        String username = (String) authentication.getPrincipal();

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        return UserMapper.mapToUserProfileDto(customUserDetails);
    }

    public void logout(HttpServletRequest request) {

        // Obtiene token jwt desde el request y valida que NO sea null y que contenga texto
        // de lo contrario no continua con la logica del metodo
        String jwt = jwtService.extractJwtFromRequest(request);
        if (jwt == null || !StringUtils.hasText(jwt)) {
            return;
        }

        // A partir del token obtenido desde el request, lo busca en db
        Optional<TokenJwt> tokenFromDB = jwtRepository.findByToken(jwt);

        // Valida que token obtenido desde db exista y que sea valido para asi poder invalidar el token (hacer logout)
        if (tokenFromDB.isPresent() && tokenFromDB.get().isValid()) {
            // Invalida token
            tokenFromDB.get().setValid(false);
            // Actualiza en db token invalidado
            jwtRepository.save(tokenFromDB.get());
        }

    }
}
