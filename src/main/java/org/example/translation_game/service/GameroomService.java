package com.example.translation_game.service;

import com.example.translation_game.entity.Gameroom;
import com.example.translation_game.repository.GameroomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameroomService {

    private final GameroomRepository gameroomRepository;

    public GameroomService(GameroomRepository gameroomRepository) {
        this.gameroomRepository = gameroomRepository;
    }

    // 방 리스트 조회
    public List<Gameroom> getGamerooms(String status, String search, String sort) {
        if (status != null && search != null) {
            return gameroomRepository.findByStatusAndRoomNameContainingOrderByCreatedAtDesc(status, search);
        } else if (status != null) {
            return gameroomRepository.findByStatusOrderByCreatedAtDesc(status);
        } else if (search != null) {
            return gameroomRepository.findByRoomNameContainingOrderByCreatedAtDesc(search);
        } else {
            return gameroomRepository.findAllByOrderByCreatedAtDesc();
        }
    }

    // 방 생성
    public Gameroom createGameroom(Gameroom gameroom) {
        gameroom.setCurrentMember(0); // 새 방 생성 시 현재 인원은 0
        gameroom.setStatus("waiting"); // 기본 상태는 대기중
        gameroom.setCreatedAt(LocalDateTime.now()); // 생성 시간 설정
        return gameroomRepository.save(gameroom);
    }

    // 특정 방 조회
    public Gameroom getGameroomById(Long id) {
        return gameroomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    // 방에 참여
    public Gameroom joinGameroom(Long id) {
        Gameroom gameroom = getGameroomById(id);
        if (gameroom.getCurrentMember() < gameroom.getMaxMember()) {
            gameroom.setCurrentMember(gameroom.getCurrentMember() + 1);
            return gameroomRepository.save(gameroom);
        } else {
            throw new RuntimeException("Room is full");
        }
    }

    // 방에서 나가기
    public Gameroom leaveGameroom(Long id) {
        Gameroom gameroom = getGameroomById(id);
        if (gameroom.getCurrentMember() > 0) {
            gameroom.setCurrentMember(gameroom.getCurrentMember() - 1);
            return gameroomRepository.save(gameroom);
        } else {
            throw new RuntimeException("No players to leave");
        }
    }
}

