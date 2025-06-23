package org.example.translation_game.service;

import org.example.translation_game.model.Gameroom;
import org.example.translation_game.repository.GameroomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameroomService {
    private final GameroomRepository gameroomRepository;

    public GameroomService(GameroomRepository gameroomRepository) {
        this.gameroomRepository = gameroomRepository;
    }

    public Gameroom createRoom(Gameroom gameroom) {
        gameroom.setStatus("WAIT");
        return gameroomRepository.save(gameroom);
    }

    public List<Gameroom> findByRoomName(String roomName) {
        return gameroomRepository.findAll().stream()
                .filter(room -> room.getRoomName().equals(roomName))
                .toList();
    }
}
