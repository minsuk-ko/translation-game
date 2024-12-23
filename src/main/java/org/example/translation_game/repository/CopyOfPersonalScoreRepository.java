package org.example.translation_game.repository;

import org.example.translation_game.model.CopyOfPersonalScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CopyOfPersonalScoreRepository extends JpaRepository<CopyOfPersonalScore, Long> {
    // 특정 사용자 점수 데이터 조회
    Optional<CopyOfPersonalScore> findByUserId(Long userId);

    // 점수 데이터가 존재하는지 확인
    boolean existsByUserId(Long userId);
}
