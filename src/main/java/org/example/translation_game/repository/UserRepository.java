package org.example.translation_game.repository;

import org.example.translation_game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    String findPasswordByEmail(String email);
    boolean existsByEmail(String email);
}
