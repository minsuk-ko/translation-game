package org.example.translation_game.repository;

import org.example.translation_game.model.GameRecord;
import org.example.translation_game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {
    List<GameRecord> findByUser(User user);
}
