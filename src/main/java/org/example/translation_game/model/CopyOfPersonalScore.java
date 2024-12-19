package org.example.translation_game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CopyOfpersonalScore")
public class CopyOfPersonalScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long scoreId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_plays", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer totalPlays;

    @Column(name = "total_score_sum", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer totalScoreSum;

    @Column(name = "high_score", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer highScore;
}


