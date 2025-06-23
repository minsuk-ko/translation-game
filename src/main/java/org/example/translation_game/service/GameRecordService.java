package org.example.translation_game.service;

import org.example.translation_game.model.GameRecord;
import org.example.translation_game.model.User;
import org.example.translation_game.repository.GameRecordRepository;
import org.example.translation_game.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GameRecordService {
    private final GameRecordRepository gameRecordRepository;
    private final UserRepository userRepository;

    public GameRecordService(GameRecordRepository gameRecordRepository, UserRepository userRepository) {
        this.gameRecordRepository = gameRecordRepository;
        this.userRepository = userRepository;
    }

    public GameRecord saveRecord(String email, String content) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        GameRecord record = new GameRecord();
        record.setUser(user);
        record.setContent(content);
        return gameRecordRepository.save(record);
    }

    public List<GameRecord> getRecords(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return Collections.emptyList();
        }
        return gameRecordRepository.findByUser(user);
    }
}
