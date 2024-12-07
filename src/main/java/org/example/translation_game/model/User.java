package org.example.translation_game.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
@SequenceGenerator(
        name="USER_SEQ_GEN",
        sequenceName="USER_SEQ",
        initialValue=1,
        allocationSize=1
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "USER_SEQ_GEN")
    @Column(name = "userId")
    private long userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
}
