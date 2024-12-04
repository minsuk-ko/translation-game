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


    public boolean existByUserEmail(String email) {
        return userRepository.existByUserEmail(email);
    }
    public boolean checkPassword(String email, String password) {
        return userRepository.checkPassword(email, password);
    }

    /**회원 가입*/
    public void signUp(User user) {
        userRepository.save(user);
    }

}
