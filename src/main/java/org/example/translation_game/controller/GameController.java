package org.example.translation_game.controller;

import jakarta.servlet.http.HttpSession;
import org.example.translation_game.model.User;
import org.example.translation_game.model.CopyOfPersonalScore;
import org.example.translation_game.service.CopyOfPersonalScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GameController {

    private final CopyOfPersonalScoreService copyOfPersonalScoreService;

    public GameController(CopyOfPersonalScoreService copyOfPersonalScoreService) {
        this.copyOfPersonalScoreService = copyOfPersonalScoreService;
    }

    @GetMapping("/mypage")
    public String showMyPage(HttpSession session, Model model) {
        // 세션에서 사용자 정보 가져오기
        User loggedInUser = (User) session.getAttribute("user");

        // 로그인 상태 확인
        if (loggedInUser == null) {
            model.addAttribute("message", "로그인이 필요합니다.");
            return "redirect:/login";
        }

        try {
            // 사용자 ID로 점수 데이터 가져오기 (없으면 생성)
            Long userId = loggedInUser.getUserId();
            CopyOfPersonalScore copyOfPersonalScore = copyOfPersonalScoreService.getOrCreateScore(userId);

            // 전체 점수 데이터 가져오기
            List<CopyOfPersonalScore> scores = copyOfPersonalScoreService.getAllScores();

            // 모델에 데이터 추가
            model.addAttribute("score", copyOfPersonalScore);
            model.addAttribute("scores", scores);
            model.addAttribute("username", loggedInUser.getUsername());
        } catch (Exception e) {
            // 에러 발생 시 로그 남기고 에러 페이지로 이동
            e.printStackTrace(); // 실제 환경에서는 SLF4J 또는 로그 관리 도구로 대체
            /*model.addAttribute("error", "데이터를 로드하는 중 오류가 발생했습니다. 다시 시도해주세요.");
            return "error"; // error.html로 매핑 필요*/
        }

        return "mypage"; // mypage.html로 이동
    }
}