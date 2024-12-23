package org.example.translation_game.repository;

import org.example.translation_game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 이메일로 사용자 정보를 조회
     */
    Optional<User> findByEmail(String email);

    /**
     * 이메일로 비밀번호를 조회 (JPQL 사용)
     */
    @Query("SELECT u.password FROM User u WHERE u.email = :email")
    Optional<String> findPasswordByEmail(@Param("email") String email);

    /**
     * 이메일이 존재하는지 확인
     */
    boolean existsByEmail(String email);
}



