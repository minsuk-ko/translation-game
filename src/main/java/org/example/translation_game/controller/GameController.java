package org.example.translation_game.controller;

import org.example.translation_game.model.User;
import org.example.translation_game.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /** Start a new game with the given number of rounds. */
    @PostMapping("/start")
    public String startGame(@RequestParam(defaultValue = "3") int rounds) {
        gameService.startGame(rounds);
        return gameService.nextSentence();
    }

    /** Get the next sentence for translation. */
    @GetMapping("/sentence")
    public String nextSentence() {
        return gameService.nextSentence();
    }

    /** Submit a translation for the current sentence. */
    @PostMapping("/translate")
    public int submit(@RequestParam long userId, @RequestParam String text) {
        User user = new User();
        user.setUserId(userId);
        return gameService.submitTranslation(user, text);
    }
}
