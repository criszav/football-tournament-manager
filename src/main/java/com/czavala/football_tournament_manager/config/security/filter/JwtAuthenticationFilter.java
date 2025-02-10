package com.czavala.football_tournament_manager.config.security.filter;

import com.czavala.football_tournament_manager.persistance.entity.TokenJwt;
import com.czavala.football_tournament_manager.persistance.repository.TokenJwtRepository;
import com.czavala.football_tournament_manager.service.auth.JwtService;
import com.czavala.football_tournament_manager.service.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final TokenJwtRepository jwtRepository;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService, TokenJwtRepository jwtRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.jwtRepository = jwtRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Extrae token jwt desde header 'Authentication' del request
        String token = jwtService.extractJwtFromRequest(request);

        // Si token extraído desde el request es null o no contiene texto,
        // vuelve a la cadena de filtros y no continua con la lógica de nuestro filtro
        if (token == null || !StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Luego de obtener token desde el request, busca ese token en db
        Optional<TokenJwt> jwt = jwtRepository.findByToken(token);
        // Valida que el token obtenido desde db sea valido
        boolean isValid = validateToken(jwt);

        // Si token NO es valido, vuelve a la cadena de filtros y NO continua con JwtAuthenticationFilter
        if (!isValid) {
            filterChain.doFilter(request, response);
            return;
        }

        // Obtiene subject/username desde token (a su vez valida token: formato, firma, fecha expiracion)
        // Al extraer el subject (username) desde el token,
        // se valida a su vez que la firma del token sea valida, el formato del token y la fecha de expiración
        String username = jwtService.extractUsernameFromToken(token);

        // Obtiene datos del user segun username extraído del token
        UserDetails user = userDetailsService.loadUserByUsername(username);

        // Configura objeto Authentication dentro del SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                null,
                user.getAuthorities()
        );

        // Agrega detalles de autenticación al token de Authentication desde request
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // Vuelve a la cadena de filtros y jecuta resto de filtros
        filterChain.doFilter(request, response);
    }

    private boolean validateToken(Optional<TokenJwt> jwt) {

        if (!jwt.isPresent()) {
            System.out.println("\nToken no fue encontrado en db o no fue generado por nuestro sistema\n");
            return false;
        }
        
        TokenJwt token = jwt.get();

        // Obtiene fecha actual del sistema
        Date now = new Date(System.currentTimeMillis());

        // Valida que fecha de expiracion del token sea posterior a la fecha actual del sistema,
        // es decir, que token no haya expirado
        boolean isValid = token.isValid() && token.getExpiration().after(now);

        if (!isValid) {
            System.out.println("\nToken inválido\n");
            updateTokenStatus(token);
        }

        return isValid;
    }

    private void updateTokenStatus(TokenJwt token) {
        // Invalida token y lo actualiza en db
        token.setValid(false);
        jwtRepository.save(token);
    }
}
