package org.example.translation_game.controller;

import jakarta.servlet.http.HttpSession;
import org.example.translation_game.model.User;
import org.example.translation_game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /** 로그인 */
    @PostMapping("/login_pro")
    public String login_pro(@RequestParam(value="email") String email,
                            @RequestParam(value="password") String password, Model model, HttpSession session) {
        if(email==null||password==null){
            return "redirect:/login";
        }
        if(!userService.existEmail(email)) {
            model.addAttribute("message", "존재하지 않는 이메일입니다.");
            return "redirect:/login";
        }
        if (userService.checkPasswordByEmail(email, password)) {
            session.setAttribute("userLoginSession",userService.getUserByEmail(email));
            User user =(User)session.getAttribute("userLoginSession");
            System.out.println(user.getEmail());
            return "login_success";
        } else {
            model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
            return "redirect:/login";
        }
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    /** 회원가입 */
    @PostMapping("/api/signUp_pro")
    public String signUp_pro(Model model, @ModelAttribute("user") User user) {
        if(user==null){
            return "redirect:/signup";
        }
        if (userService.existEmail(user.getEmail())) {
            model.addAttribute("message", "이미 존재하는 이메일입니다.");
            model.addAttribute("user",new User());
            return "redirect:/signup";
        } else {
            userService.signUp(user);
            return "login";
        }
    }
}
