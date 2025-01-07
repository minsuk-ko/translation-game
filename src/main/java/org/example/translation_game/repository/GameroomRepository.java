package com.example.game.repository;

import com.example.game.entity.Gameroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameroomRepository extends JpaRepository<Gameroom, Long> {

    List<Gameroom> findByStatusOrderByCreatedAtDesc(String status);

    List<Gameroom> findByRoomNameContainingOrderByCreatedAtDesc(String search);

    List<Gameroom> findByStatusAndRoomNameContainingOrderByCreatedAtDesc(String status, String search);

    List<Gameroom> findAllByOrderByCreatedAtDesc();
}
