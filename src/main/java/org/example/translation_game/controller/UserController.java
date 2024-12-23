package org.example.translation_game.controller;

import org.example.translation_game.model.User;
import org.example.translation_game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("message", ""); // 초기 메시지
        model.addAttribute("user", new User()); // 빈 User 객체 전달
        return "login";
    }

    @PostMapping("/login_pro")
    public String loginPro(@RequestParam("email") String email,
                           @RequestParam("password") String password,
                           Model model,
                           HttpSession session) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("message", "이메일과 비밀번호를 입력해주세요.");
            return "login";
        }

        if (!userService.existEmail(email)) {
            model.addAttribute("message", "존재하지 않는 이메일입니다.");
            return "login";
        }

        if (userService.checkPasswordByEmail(email, password)) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            Optional<User> optionalUser = userService.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                session.setAttribute("user", optionalUser.get()); // 세션에 사용자 정보 저장
                return "redirect:/mainroom"; // 성공 시 mainroom.html로 이동
            } else {
                model.addAttribute("message", "사용자 정보를 불러오는데 실패했습니다.");
                return "login";
            }
        } else {
            model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
            return "login";
        }
    }

    @GetMapping("/mainroom")
    public String mainRoom(HttpSession session, Model model) {
        // 세션에서 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // 로그인하지 않은 상태이면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        // 사용자 정보를 모델에 추가
        model.addAttribute("user", user);
        return "mainroom";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User()); // 빈 User 객체 추가
        return "signup";
    }

    @PostMapping("/signup_pro")
    public String signUpPro(@ModelAttribute("user") User user, Model model) {
        if (userService.existEmail(user.getEmail())) {
            model.addAttribute("message", "이미 존재하는 이메일입니다.");
            model.addAttribute("user", user);
            return "signup";
        }

        userService.signUp(user); // 회원가입 처리
        model.addAttribute("message", "회원가입이 완료되었습니다. 로그인해주세요.");
        return "login"; // 회원가입 성공 후 로그인 페이지로 이동
    }
}

