package org.example.translation_game.service;

import org.example.translation_game.model.CopyOfPersonalScore;
import org.example.translation_game.model.User;
import org.example.translation_game.repository.CopyOfPersonalScoreRepository;
import org.example.translation_game.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyOfPersonalScoreService {

    private final CopyOfPersonalScoreRepository copyOfPersonalScoreRepository;
    private final UserRepository userRepository;

    public CopyOfPersonalScoreService(CopyOfPersonalScoreRepository copyOfPersonalScoreRepository, UserRepository userRepository) {
        this.copyOfPersonalScoreRepository = copyOfPersonalScoreRepository;
        this.userRepository = userRepository;
    }

    /**
     * 사용자 ID를 기반으로 점수 데이터를 조회하거나, 없으면 새로 생성합니다.
     * @param userId 사용자 ID
     * @return CopyOfPersonalScore 객체
     */
    public CopyOfPersonalScore createScore(Long userId) {
        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));

        // 점수 객체 생성
        CopyOfPersonalScore copyOfPersonalScore = new CopyOfPersonalScore();
        copyOfPersonalScore.setUser(user); // User와 매핑
        copyOfPersonalScore.setTotalPlays(0);
        copyOfPersonalScore.setTotalScoreSum(0);
        copyOfPersonalScore.setHighScore(0);

        // User 엔티티와 연결 (양방향 매핑 시 필요)
        user.setPersonalScore(copyOfPersonalScore);

        // 저장
        return copyOfPersonalScoreRepository.save(copyOfPersonalScore);
    }

    /**
     * 사용자 ID를 기반으로 점수 데이터를 가져오거나, 없으면 새로 생성합니다.
     * @param userId 사용자 ID
     * @return CopyOfPersonalScore 객체
     */
    public CopyOfPersonalScore getOrCreateScore(Long userId) {
        return copyOfPersonalScoreRepository.findByUserId(userId)
                .orElseGet(() -> createScore(userId)); // 데이터가 없으면 생성
    }

    /**
     * 사용자 ID를 기반으로 점수 데이터를 조회합니다.
     * @param userId 사용자 ID
     * @return CopyOfPersonalScore 객체
     */
    public CopyOfPersonalScore getScoreByUserId(Long userId) {
        return copyOfPersonalScoreRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("점수 데이터를 찾을 수 없습니다. 사용자 ID: " + userId));
    }

    /**
     * 사용자 점수를 업데이트합니다.
     * @param userId 사용자 ID
     * @param newScore 새로운 점수
     * @return 업데이트된 CopyOfPersonalScore 객체
     */
    public CopyOfPersonalScore updateScore(Long userId, Integer newScore) {
        // 기존 점수 데이터 가져오기
        CopyOfPersonalScore copyOfPersonalScore = getScoreByUserId(userId);

        // 총 플레이 수 증가
        copyOfPersonalScore.setTotalPlays(copyOfPersonalScore.getTotalPlays() + 1);

        // 총 점수 합산
        copyOfPersonalScore.setTotalScoreSum(copyOfPersonalScore.getTotalScoreSum() + newScore);

        // 최고 점수 갱신
        if (newScore > copyOfPersonalScore.getHighScore()) {
            copyOfPersonalScore.setHighScore(newScore);
        }

        // 업데이트 후 저장
        return copyOfPersonalScoreRepository.save(copyOfPersonalScore);
    }

    /**
     * 모든 사용자 점수를 조회합니다.
     * @return List<CopyOfPersonalScore> 모든 점수 데이터
     */
    public List<CopyOfPersonalScore> getAllScores() {
        return copyOfPersonalScoreRepository.findAll();
    }
}
