package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.auth.register.SaveManagerDto;
import com.czavala.football_tournament_manager.exception.InvalidPasswordException;
import com.czavala.football_tournament_manager.persistance.entity.User;
import com.czavala.football_tournament_manager.persistance.repository.UserRepository;
import com.czavala.football_tournament_manager.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerOneManager(SaveManagerDto newManagerDto) {

        // Antes de registrar al nuevo manager (user), valida que la claves sean validas y coincidan
        validatePassword(newManagerDto);

        // Este User representa un Manager
        User user = new User();
        user.setFirstname(newManagerDto.getFirstname());
        user.setLastname(newManagerDto.getLastname());
        user.setUsername(newManagerDto.getUsername());
        user.setEmail(newManagerDto.getEmail());

        // Codifica la clave utilizando la implementación BCryptPasswordEnconder
        // que fue inyectada al AuthenticationProvider en la clase SecurityContig
        user.setPassword(passwordEncoder.encode(newManagerDto.getPassword()));

        user.setRun(newManagerDto.getRun());
        user.setActive(newManagerDto.getIsActive());
        user.setUserRoleId(newManagerDto.getUserRoleId());
        user.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));

        return userRepository.save(user);
    }

    // Valida que los campos password y repeatedPassword sean validos, al momento de registrar un user
    private void validatePassword(SaveManagerDto newManagerDto) {

        // Verifica que ambos campos (password y repeatedPassword) NO esten vacios
        if (!StringUtils.hasText(newManagerDto.getPassword()) || !StringUtils.hasText(newManagerDto.getRepeatedPassword())) {
            throw new InvalidPasswordException("Passwords do not match.");
        }

        // Verifica que ambas password (password y repeatedPassword) ingresadas coincidan
        if (!newManagerDto.getPassword().equals(newManagerDto.getRepeatedPassword())) {
            throw new InvalidPasswordException("Passwords do not match.");
        }

    }
}
