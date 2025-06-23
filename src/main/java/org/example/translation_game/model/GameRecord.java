package org.example.translation_game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_record")
@Getter
@Setter
@SequenceGenerator(name = "GAME_RECORD_SEQ_GEN", sequenceName = "GAME_RECORD_SEQ", initialValue = 1, allocationSize = 1)
public class GameRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GAME_RECORD_SEQ_GEN")
    @Column(name = "recordId")
    private Long recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "playedAt")
    private LocalDateTime playedAt = LocalDateTime.now();
}
