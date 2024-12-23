package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_user_role")
public class UserRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", unique = true, length = 50, nullable = false)
    private String roleName;

    private String description;

    @OneToMany(mappedBy = "userRole")
    private List<User> users;
}
