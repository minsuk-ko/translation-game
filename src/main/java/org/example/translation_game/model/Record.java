package model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Record {
    int userId;
    int totalGames;
    int totalScoreSum;
    int highScore;
}
