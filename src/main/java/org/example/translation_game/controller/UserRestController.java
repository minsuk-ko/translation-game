package org.example.translation_game.controller;

import org.apache.coyote.Response;
import org.example.translation_game.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    //이메일 중복검사
    @GetMapping("/emailCheck")
    public ResponseEntity<Boolean> emailCheck(@RequestParam("email") String email) {
        System.out.println("emailCheck 작동");
        boolean isEmailAvailable=userService.emailAvailable(email);
        return ResponseEntity.ok(isEmailAvailable);
    }
}
