package org.example.translation_game.repository;

import org.example.translation_game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existByUserEmail(String email);
    boolean checkPassword(String email, String password);
}
