package org.example.translation_game.repository;

import org.example.translation_game.model.Gameroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameroomRepository extends JpaRepository<Gameroom, Long> {
}
