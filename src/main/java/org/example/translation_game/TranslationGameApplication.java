package org.example.translation_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class TranslationGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(TranslationGameApplication.class, args);
        System.out.println("실행 성공");

    }
}
