package org.example.translation_game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "score")
@Getter
@Setter
@SequenceGenerator(
        name = "SCORE_SEQ_GEN",
        sequenceName = "SCORE_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCORE_SEQ_GEN")
    @Column(name = "scoreId")
    private Long scoreId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "value")
    private int value;
}
