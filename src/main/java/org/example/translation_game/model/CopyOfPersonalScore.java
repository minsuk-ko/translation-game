package org.example.translation_game.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "copy_of_personal_score")
public class CopyOfPersonalScore {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId // User의 ID를 공유
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_plays", nullable = false)
    private Integer totalPlays;

    @Column(name = "total_score_sum", nullable = false)
    private Integer totalScoreSum;

    @Column(name = "high_score", nullable = false)
    private Integer highScore;

    // Default Constructor
    public CopyOfPersonalScore() {}

    // Constructor with fields
    public CopyOfPersonalScore(User user, Integer totalPlays, Integer totalScoreSum, Integer highScore) {
        this.user = user;
        this.userId = user.getUserId(); // User의 ID와 동기화
        this.totalPlays = totalPlays;
        this.totalScoreSum = totalScoreSum;
        this.highScore = highScore;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getUserId(); // User와 ID를 동기화
    }

    public Integer getTotalPlays() {
        return totalPlays;
    }

    public void setTotalPlays(Integer totalPlays) {
        this.totalPlays = totalPlays;
    }

    public Integer getTotalScoreSum() {
        return totalScoreSum;
    }

    public void setTotalScoreSum(Integer totalScoreSum) {
        this.totalScoreSum = totalScoreSum;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    // equals() and hashCode() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyOfPersonalScore that = (CopyOfPersonalScore) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}

