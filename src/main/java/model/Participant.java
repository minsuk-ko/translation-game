package model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
@Slf4j
@Data
public class Participant {
    int gameroomId;
    int userId;
    Date joinAt;
    int score;
    boolean isHost;
    boolean isReady;
}
