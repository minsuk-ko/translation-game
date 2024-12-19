package org.example.translation_game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "gameroom")
public class Gameroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_name", nullable = false, length = 25)
    private String roomName;

    @Column(name = "max_member", nullable = false)
    private Integer maxMember;

    @Column(name = "current_member", nullable = false)
    private Integer currentMember;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "level", nullable = false, length = 25)
    private String level;

    @Column(name = "content", nullable = false, length = 255)
    private String content;

    @Column(name = "language", nullable = false)
    private Boolean language;
}

