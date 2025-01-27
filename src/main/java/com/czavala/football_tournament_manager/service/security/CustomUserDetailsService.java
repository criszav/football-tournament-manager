package com.czavala.football_tournament_manager.service.security;

import com.czavala.football_tournament_manager.config.security.CustomUserDetails;
import com.czavala.football_tournament_manager.persistance.entity.User;
import com.czavala.football_tournament_manager.persistance.entity.UserRole;
import com.czavala.football_tournament_manager.persistance.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Busca en db a usuario (por username) que esta intentando autenticarse
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("From CustomUserDetailsService: user not found. Username: " + username));

        // Obtiene rol asignado al usuario y verifica que tenga uno asignado ( !=null )
        UserRole userRole = user.getUserRole();
        if (userRole == null) throw new AuthenticationServiceException("From CustomUserDetailsService: user has no role assigned.");

        // Crea y retorna un CustomUserDetails (implementa UserDetails) con los datos del usuario autenticado
        return new CustomUserDetails(user);
    }
}
