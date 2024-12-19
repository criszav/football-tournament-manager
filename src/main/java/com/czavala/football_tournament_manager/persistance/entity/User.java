package com.czavala.football_tournament_manager.persistance.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false, length = 9)
    private String run;

    @Column(name = "is_active", nullable = false)
    // indica si user esta activo en el sistema (true) o si se dió de baja su cuenta (false)
    private boolean isActive;

    @OneToOne(mappedBy = "user")
    private Team team;

    @OneToMany(mappedBy = "user")
    private List<Tournament> tournaments;

    @Column(name = "user_role_id", nullable = false)
    private Long userRoleId;

    @ManyToOne
    @JoinColumn(name = "user_role_id", insertable = false, updatable = false)
    private UserRole userRole;
}
