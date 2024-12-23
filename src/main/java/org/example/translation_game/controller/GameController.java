package org.example.translation_game.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/gameRoom")
    public String gameRoom() {
        return "/gameRoom";
    }

    @GetMapping("/readyRoom")
    public String readyRoom() {
        return "/readyRoom";
    }

    @GetMapping("/mainroom")
    public String mainroom() {
        return "/mainroom";
    }
}
