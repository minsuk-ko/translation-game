package org.example.translation_game.controller;

import org.example.translation_game.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/generate")
    public String generateText(@RequestParam String prompt) {
        return openAIService.generateResponse(prompt);
    }
}
