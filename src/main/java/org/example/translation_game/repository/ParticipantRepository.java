package org.example.translation_game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.translation_game.model.Participant;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByGameroomRoomId(Long roomId);
    List<Participant> findByUserUserId(Long userId);
    void deleteByUserUserIdAndGameroomRoomId(Long userId, Long roomId);
}
