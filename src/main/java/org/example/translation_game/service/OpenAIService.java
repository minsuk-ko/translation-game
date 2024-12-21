package org.example.translation_game.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import org.example.translation_game.dto.OpenAIRequest;
import org.example.translation_game.dto.OpenAIResponse;

@Service
public class OpenAIService { // openai api와 통신하기 위한 서비스 클래스

    @Value("${openai.api.key}") // application.properties에서 선언
    private String apiKey; // 인증 키를 저장하는 변수

    private final RestTemplate restTemplate; // restTemplate 주입 생성자

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateResponse(String prompt) { // 응답 텍스트를 반환하는 메서드
        String url = "https://api.openai.com/v1/completions";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders(); // 요청의 헤더 정보 객체 생성
        headers.set("Authorization", apiKey); // 인증 헤더에 Bearer 토큰 방식의 api키 추가
        headers.set("Content-Type", "application/json"); // 요청 데이터 형식을 json으로

        // 요청 본문 설정
        OpenAIRequest request = new OpenAIRequest(); // 요청 데이터를 담는 dto
        request.setModel("text-davinci-003"); // 사용 모델 설정
        request.setPrompt(prompt); // 사용 프롬포트 설정
        request.setMaxTokens(150);
        request.setTemperature(0.7);

        // HTTP 요청 엔터티 생성
        HttpEntity<OpenAIRequest> entity = new HttpEntity<>(request, headers);

        // OpenAI API 호출
        ResponseEntity<OpenAIResponse> responseEntity = restTemplate.postForEntity(url, entity, OpenAIResponse.class);
        OpenAIResponse response = responseEntity.getBody(); // 응답 본문을 OpenAIResponse 객체로 추출

        // 응답 처리
        if (response != null && !response.getChoices().isEmpty()) { // 응답이 null이 아니고 choice가 비어있지 않을때
            return response.getChoices().get(0).getText().trim(); // 첫 번째 텍스트를 가져와 앞뒤 공백을 제거하고 반환
        }
        return "No response from OpenAI.";
    }
}

