package org.example.translation_game.repository;

import org.example.translation_game.model.CopyOfPersonalScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CopyOfPersonalScoreRepository extends JpaRepository<CopyOfPersonalScore, Long> {
    Optional<CopyOfPersonalScore> findByUserUserId(Long userId);
}
