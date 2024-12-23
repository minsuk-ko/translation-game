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

    /**이메일 중복검사*/
    public boolean emailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    public boolean existEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    public boolean checkPasswordByEmail(String email, String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            return user.getPassword().equals(password);
        }
        return false;
    }

    public User getUserByEmail(String email) {
        if(userRepository.findByEmail(email).isPresent()) {
            System.out.println(userRepository.findByEmail(email).get().getEmail());
        }
        return userRepository.findByEmail(email).get();
    }

    /**회원 가입*/
    public void signUp(User user) {
        User saveUser = new User();
        saveUser.setUserId(null);
        saveUser.setUsername(user.getUsername());
        saveUser.setPassword(user.getPassword());
        saveUser.setEmail(user.getEmail());

        userRepository.save(saveUser);
    }



}
