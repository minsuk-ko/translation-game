package org.example.translation_game.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private CopyOfPersonalScore personalScore;

    // Default Constructor
    public User() {}

    // Constructor with fields
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CopyOfPersonalScore getPersonalScore() {
        return personalScore;
    }

    public void setPersonalScore(CopyOfPersonalScore personalScore) {
        this.personalScore = personalScore;
        if (personalScore != null) {
            personalScore.setUser(this);
        }
    }

    // equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
    @PostPersist
    public void createPersonalScore() {
        if (this.personalScore == null) {
            this.personalScore = new CopyOfPersonalScore();
            this.personalScore.setUser(this);
            this.personalScore.setTotalPlays(0);
            this.personalScore.setTotalScoreSum(0);
            this.personalScore.setHighScore(0);
        }
    }
}

