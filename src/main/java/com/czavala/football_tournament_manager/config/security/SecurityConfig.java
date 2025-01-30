package com.czavala.football_tournament_manager.config.security;

import com.czavala.football_tournament_manager.config.security.filter.JwtAuthenticationFilter;
import com.czavala.football_tournament_manager.service.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Desahbilita csrf, ya que el sistema es STATELESS
                .csrf(csrf -> csrf.disable())

                // Indica que la app es STATELESS (basada en tokens jwt), Spring Security NO crea un HttpSession
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authenticationProvider(authenticationProvider())

                // Aplica nuestro filtro personalizado
                // antes de que se aplique el filtro UsernamePasswordAuthenticationFilter en la cadena de filtros
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(authRequest -> {
                    authRequest.requestMatchers(HttpMethod.GET, "/home/public").permitAll();
                    authRequest.requestMatchers(HttpMethod.GET, "/home/logout").permitAll();

                    // Endpoint para login es publico
                    authRequest.requestMatchers(HttpMethod.POST, "/authenticate/login").permitAll();
                    authRequest.requestMatchers(HttpMethod.GET, "/authenticate/**").permitAll();

                    // Sólo admin y superadmin pueden registrar a los managers
                    authRequest.requestMatchers(HttpMethod.POST, "/manager/register")
                            .hasAnyRole("ADMIN", "SUPER_ADMIN");


                    authRequest.anyRequest().authenticated();
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

}
