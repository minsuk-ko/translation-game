package org.example.translation_game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name="gameroom")
@Getter
@Setter
@SequenceGenerator(name="GAMEROOM_SEQ_GEN", sequenceName="GAMEROOM_SEQ", initialValue=1, allocationSize=1)
public class Gameroom {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GAMEROOM_SEQ_GEN")
    @Column(name="roomId")
    private Long roomId;

    @Column(name="roomname")
    private String roomName;

    @Column(name="maxMember")
    private int maxMember;

    @Column(name="currentMember")
    private int currentMember = 0;

    @Column(name="status")
    private String status;

    @Column(name="createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name="level")
    private String level;

    @Column(name="content")
    private String content;

    @Column(name="language")
    private boolean language;
}
