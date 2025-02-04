package com.czavala.football_tournament_manager.config.security.filter;

import com.czavala.football_tournament_manager.service.auth.JwtService;
import com.czavala.football_tournament_manager.service.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Obtener header "Authorization" desde el request
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Extraer token desde header
        // Extrae token desde header 'Authorization' del request
        // Header tiene formato 'Bearer <token>'
        // Divide header segun un espacio, entonces hay dos items: Bearer (posicion 0) y jwt (posicion 1)
        String token = authHeader.split(" ")[1];

        // 3. Obtener subject/username desde token (a su vez valida token: formato, firma, fecha expiracion)
        // Al extraer el subject (username) desde el token,
        // se valida a su vez que la firma del token sea valida, el formato del token y la fecha de expiración
        String username = jwtService.extractUsernameFromToken(token);

        // Obtiene datos del user segun username extraído del token
        UserDetails user = userDetailsService.loadUserByUsername(username);

        // 4. Setear objeto Authentication dentro del SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                null,
                user.getAuthorities()
        );

        // Agrega detalles de autenticación al token de Authentication desde request
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 5. Vuelve a la cadena de filtros y jecuta resto de filtros
        filterChain.doFilter(request, response);
    }
}
