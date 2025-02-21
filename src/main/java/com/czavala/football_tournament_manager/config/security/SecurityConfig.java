package com.czavala.football_tournament_manager.config.security;

import com.czavala.football_tournament_manager.config.security.filter.JwtAuthenticationFilter;
import com.czavala.football_tournament_manager.config.security.handler.CustomAccessDeniedHandler;
import com.czavala.football_tournament_manager.config.security.handler.CustomAuthenticationEntryPoint;
import com.czavala.football_tournament_manager.service.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(
            CustomUserDetailsService userDetailsService,
            JwtAuthenticationFilter jwtAuthFilter,
            CustomAuthenticationEntryPoint authenticationEntryPoint,
            CustomAccessDeniedHandler accessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Desahbilita csrf, ya que el sistema es STATELESS
                .csrf(csrf -> csrf.disable())

                // Indica que la app es STATELESS (basada en tokens jwt), Spring Security NO crea un HttpSession
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Aplica nuestro filtro personalizado
                // antes de que se aplique el filtro UsernamePasswordAuthenticationFilter en la cadena de filtros
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // Seguridad de endpoints utilizando coincidencia de solicitudes http
                .authorizeHttpRequests(authRequest -> {
                    getRequestMatchers(authRequest);
                })

                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(authenticationEntryPoint);
                    exception.accessDeniedHandler(accessDeniedHandler);
                });

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        daoAuthProvider.setPasswordEncoder(passwordEncoder());
        daoAuthProvider.setUserDetailsService(userDetailsService);

        return daoAuthProvider;
    }

    // Indica que BCryptPasswordEncoder es la implementación de PasswordEncoder que se utilizará
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Indica endpoints publicos y privados segun role
    private static void getRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authRequest) {

        // Authenticate
        authRequest.requestMatchers(HttpMethod.POST, "/authenticate/login").permitAll();
        authRequest.requestMatchers(HttpMethod.POST, "/authenticate/logout").permitAll();
        authRequest.requestMatchers(HttpMethod.GET, "/authenticate/validate-token").permitAll();
        authRequest.requestMatchers(HttpMethod.GET, "/authenticate/profile")
                .hasAnyRole("SUPER_ADMIN", "ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");

        // Cards
        authRequest.requestMatchers(HttpMethod.GET, "/cards/{cardId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/cards/type/{cardType}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.POST, "/cards")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.PUT, "/cards/{cardId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/cards").permitAll();

        // Goals
        authRequest.requestMatchers(HttpMethod.GET, "/goals/{goalId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.POST, "/goals")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.PUT, "/goals/{goalId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/goals/player/{playerId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/goals/player/{playerId}/team/{teamId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/goals").permitAll();

        // GoalType
        authRequest.requestMatchers("/goal-type")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.POST, "/goal-type")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.PUT, "/goal-type/{goal-type-id}")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.PUT, "/goal-type/{goal-type-id}/disable")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.GET, "/goal-type")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");

        // Match Status
        authRequest.requestMatchers("/match-status")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.POST, "/match-status")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.PUT, "/match-status/{id}")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.PUT, "/match-status/{id}/disable")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.GET, "/match-status")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");

        // Match
        authRequest.requestMatchers(HttpMethod.GET, "/matches/{matchId}")
                .permitAll();
        authRequest.requestMatchers(HttpMethod.POST, "/matches")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.PUT, "/matches/{matchId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/matches/status/{matchStatusId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/matches")
                .permitAll();

        // Players
        authRequest.requestMatchers(HttpMethod.GET, "/players/{playerId}")
                .permitAll();
        authRequest.requestMatchers(HttpMethod.POST, "/players")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.PUT, "/players/{playerId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.PUT, "/players/{playerId}/disable")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/players")
                .permitAll();

        // Teams
        authRequest.requestMatchers(HttpMethod.GET, "/teams/{teamId}")
                .permitAll();
        authRequest.requestMatchers(HttpMethod.POST, "/teams")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.PUT, "/teams/{teamId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.PUT, "/teams/{teamId}/disable")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/teams/{teamId}/cards")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/teams/{teamId}/players")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER", "TEAM_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/teams")
                .permitAll();

        // Tournament Status
        authRequest.requestMatchers("/tournament-status")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.POST, "/tournament-status")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.PUT, "/tournament-status/{id}")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.PUT, "/tournament-status/{id}/disable")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
//        authRequest.requestMatchers(HttpMethod.GET, "/tournament-status")
//                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");

        // Tournament
        authRequest.requestMatchers(HttpMethod.GET, "/tournaments/**")
                .permitAll();
        authRequest.requestMatchers(HttpMethod.POST, "/tournaments")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.PUT, "/tournaments/{tournamentId}")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.PUT, "/tournaments/{tournamentId}/disable")
                .hasAnyRole("ADMIN", "TOURNAMENT_MANAGER");
        authRequest.requestMatchers(HttpMethod.GET, "/tournaments")
                .permitAll();

        // Stats
        authRequest.requestMatchers("/stats/**").permitAll();

        // Users(ManagerController)
        authRequest.requestMatchers("/manager/register")
                .hasAnyRole("SUPER_ADMIN", "ADMIN");

        // User Role
        authRequest.requestMatchers("/user-role").hasRole("SUPER_ADMIN");

        authRequest.anyRequest().authenticated();
    }

}
