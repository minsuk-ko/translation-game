package org.example.translation_game.service;

import org.example.translation_game.model.Score;
import org.example.translation_game.model.User;
import org.example.translation_game.repository.ScoreRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class GameService {

    private final OpenAIService openAIService;
    private final ScoreRepository scoreRepository;

    private int totalRounds = 1;
    private int currentRound = 0;
    private String currentSentence;
    private final Map<Long, Integer> scoreboard = new HashMap<>();

    public GameService(OpenAIService openAIService, ScoreRepository scoreRepository) {
        this.openAIService = openAIService;
        this.scoreRepository = scoreRepository;
    }

    public void startGame(int rounds) {
        this.totalRounds = rounds;
        this.currentRound = 0;
        scoreboard.clear();
        this.currentSentence = null;
    }

    public String nextSentence() {
        if (currentRound >= totalRounds) {
            return null;
        }
        currentSentence = openAIService.generateSentence();
        currentRound++;
        return currentSentence;
    }

    public int submitTranslation(User user, String translation) {
        if (currentSentence == null) {
            return 0;
        }
        int score = openAIService.scoreTranslation(currentSentence, translation);
        scoreboard.merge(user.getUserId(), score, Integer::sum);
        if (currentRound >= totalRounds) {
            saveScores();
        }
        return score;
    }

    private void saveScores() {
        for (Map.Entry<Long, Integer> entry : scoreboard.entrySet()) {
            Score score = new Score();
            User user = new User();
            user.setUserId(entry.getKey());
            score.setUser(user);
            score.setValue(entry.getValue());
            scoreRepository.save(score);
        }
    }
}
