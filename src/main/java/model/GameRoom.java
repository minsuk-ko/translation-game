package model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
@Slf4j
@Data
public class GameRoom {
    int roomId;
    String roomName;
    int maxMember;
    int currentNum;
    String status;
    Date createAt;
    String level;
    String contents;
    String langauge;
}

