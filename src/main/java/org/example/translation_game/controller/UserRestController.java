package org.example.translation_game.controller;

import org.apache.coyote.Response;
import org.example.translation_game.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    //이메일 중복검사
    @GetMapping("/emailCheck")
    public ResponseEntity<Map<String, Object>> emailCheck(@RequestParam("email") String email) {
        boolean isEmailAvailable = userService.emailAvailable(email); // 메서드 호출
        Map<String, Object> response = new HashMap<>();
        response.put("isAvailable", isEmailAvailable);
        response.put("message", isEmailAvailable ? "사용 가능한 이메일입니다." : "이미 존재하는 이메일입니다.");
        return ResponseEntity.ok(response);
    }
}
