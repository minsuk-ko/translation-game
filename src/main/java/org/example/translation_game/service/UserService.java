package org.example.translation_game.service;

import org.example.translation_game.model.User;
import org.example.translation_game.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean existEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    public boolean checkPasswordByEmail(String email, String password) {
        return userRepository.findPasswordByEmail(email).equals(password);
    }

    /**회원 가입*/
    public void signUp(User user) {
        userRepository.save(user);
    }

}
