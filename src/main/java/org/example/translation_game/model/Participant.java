package org.example.translation_game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "participant")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participant_id")
    private Long participantId;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Gameroom gameroom;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "join_at", nullable = false)
    private LocalDateTime joinAt;

    @Column(name = "score", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer score;

    @Column(name = "is_host", nullable = false)
    private Boolean isHost;

    @Column(name = "is_ready", nullable = false)
    private Boolean isReady;
}

