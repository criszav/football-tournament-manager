package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.auth.register.SaveManagerDto;
import com.czavala.football_tournament_manager.persistance.entity.User;

public interface UserService {

    User registerOneManager(SaveManagerDto saveManagerDto);

    User findOneByUsername(String username);
}
