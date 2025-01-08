package org.example.translation_game.service;

import org.example.translation_game.dto.OpenAiRequest;
import org.example.translation_game.dto.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TranslationService {
    private final RestTemplate restTemplate;
    private final String apikey;

    public TranslationService(RestTemplateBuilder restTemplateBuilder, @Value("${openai.api.key}") String apikey) {
        this.restTemplate = restTemplateBuilder.build();
        this.apikey = apikey;
    }

    // 1. 번역할 문장 생성
    public String generateTranslation(String sourceText) {
        String url = "https://api.openai.com/v1/chat/completions";

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apikey);

        // OpenAI 요청 데이터 생성
        OpenAiRequest request = new OpenAiRequest();
        request.setModel("gpt-4");
        request.setMessages(List.of(
                new OpenAiRequest.OpenaiMessage("user", "Translate this sentence: " + sourceText)
        ));

        // HTTP 요청
        HttpEntity<OpenAiRequest> entity = new HttpEntity<>(request, headers);

        // OpenAI API 호출
        ResponseEntity<OpenAiResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, OpenAiResponse.class
        );

        // 응답 처리
        return response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();
    }

    // 2. 번역 결과 평가
    public int evaluateTranslation(String sourceText, String userTranslation) {
        String url = "https://api.openai.com/v1/chat/completions";

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apikey);

        // OpenAI 요청 데이터 생성
        OpenAiRequest request = new OpenAiRequest();
        request.setModel("gpt-4");
        request.setMessages(List.of(
                new OpenAiRequest.OpenaiMessage("user",
                        "Rate the translation accuracy:\n" +
                                "Source Text: " + sourceText + "\n" +
                                "User Translation: " + userTranslation + "\n" +
                                "Rate from 0 to 100.")
        ));

        // HTTP 요청
        HttpEntity<OpenAiRequest> entity = new HttpEntity<>(request, headers);

        // OpenAI API 호출
        ResponseEntity<OpenAiResponse> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, OpenAiResponse.class
        );

        // 응답 점수 추출
        String scoreContent = response.getBody()
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();

        // 점수 반환
        return Integer.parseInt(scoreContent.replaceAll("[^0-9]", ""));
    }

    // OpenAI 메시지 리스트 반환 (필요한 경우 추가 사용 가능)
    public List<OpenAiRequest.OpenaiMessage> getOpenaiMessage() {
        return List.of(); // 필요하면 메시지 데이터를 반환하도록 수정
    }
}
