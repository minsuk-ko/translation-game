/*
package org.example.translation_game.controller;


import com.example.translation_game.service.GameroomService;
//import com.example.translation_game.entity.Gameroom;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/gamerooms")
public class GameroomController {
    private final GameroomService gameroomService;

    public GameroomController(GameroomService gameroomService){
        this.gameroomService = gameroomService;
    }

    // 방 리스트 조회
    @GetMapping
    //public List<Gameroom> getGamerooms(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sort", required = false, defaultValue = "latest") String sort) {
        return gameroomService.getGamerooms(status, search, sort);
    }
    // 방 생성
    @PostMapping
    public Gameroom createGameroom(@RequestBody Gameroom gameroom) {
        return gameroomService.createGameroom(gameroom);
    }

    // 특정 방 조회
    @GetMapping("/{id}")
    public Gameroom getGameroomById(@PathVariable Long id) {
        return gameroomService.getGameroomById(id);
    }

    // 방에 참여
    @PostMapping("/{id}/join")
    public Gameroom joinGameroom(@PathVariable Long id) {
        return gameroomService.joinGameroom(id);
    }

    // 방에서 나가기
    @PostMapping("/{id}/leave")
    public Gameroom leaveGameroom(@PathVariable Long id) {
        return gameroomService.leaveGameroom(id);
    }

}
*/