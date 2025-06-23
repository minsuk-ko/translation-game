package org.example.translation_game.controller;

import org.example.translation_game.model.Gameroom;
import org.example.translation_game.service.GameRecordService;
import org.example.translation_game.service.GameroomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    private final GameroomService gameroomService;
    private final GameRecordService gameRecordService;

    public GameController(GameroomService gameroomService, GameRecordService gameRecordService) {
        this.gameroomService = gameroomService;
        this.gameRecordService = gameRecordService;
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

    @PostMapping("/joinRoom/{roomId}")
    public String joinRoom(@PathVariable Long roomId) {
        Gameroom room = gameroomService.findById(roomId);
        if (room != null && room.getCurrentMember() < room.getMaxMember()) {
            room.setCurrentMember(room.getCurrentMember() + 1);
            if (room.getCurrentMember() >= room.getMaxMember()) {
                room.setStatus("FULL");
            }
            gameroomService.save(room);
        }
        return "redirect:/";
    }

    @PostMapping("/saveRecord")
    public String saveRecord(@RequestParam String email, @RequestParam String content) {
        gameRecordService.saveRecord(email, content);
        return "redirect:/myPage?email=" + email;
    }
}
