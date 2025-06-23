package org.example.translation_game.controller;

import org.example.translation_game.model.Gameroom;
import org.example.translation_game.service.GameroomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    private final GameroomService gameroomService;

    public GameController(GameroomService gameroomService) {
        this.gameroomService = gameroomService;
    }

    @GetMapping("/createRoom")
    public String createRoomForm(Model model) {
        model.addAttribute("gameroom", new Gameroom());
        return "mainroom";
    }

    @PostMapping("/createRoom")
    public String createRoom(@ModelAttribute Gameroom gameroom) {
        gameroomService.createRoom(gameroom);
        return "redirect:/";
    }
}
