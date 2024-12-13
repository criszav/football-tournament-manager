package com.czavala.football_tournament_manager.persistance.entity;

import com.czavala.football_tournament_manager.utils.CardType;
import jakarta.persistence.*;

@Entity
@Table(name = "t_card")
public class Card {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false)
    private CardType cardType;
}
