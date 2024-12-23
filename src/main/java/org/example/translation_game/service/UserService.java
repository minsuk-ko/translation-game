package org.example.translation_game.service;

import org.example.translation_game.model.User;
import org.example.translation_game.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void signUp(User user) {
        userRepository.save(user);
    }
    public boolean emailAvailable(String email) {
        // 이메일이 존재하지 않으면 사용 가능 (true)
        return !existEmail(email);
    }


    public boolean checkPasswordByEmail(String email, String password) {
        Optional<String> storedPassword = userRepository.findPasswordByEmail(email);
        return storedPassword.map(pwd -> pwd.equals(password)).orElse(false);
    }
}

