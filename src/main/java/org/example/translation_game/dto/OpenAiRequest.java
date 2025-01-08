package org.example.translation_game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.*;

public class OpenAiRequest { // openai api와 통신할 데이터를 정의
    @JsonProperty("model") // model 을 text-davinci-003으로 매핑
    private String model;

    @JsonProperty("prompt") // 매핑할 프롬포트
    private String prompt;

    @JsonProperty("messages")
    private List<OpenaiMessage> messages;

    @JsonProperty("max_tokens") // 최대 토큰수 150개 매핑
    private int maxTokens;

    @JsonProperty("temperature") // 생성 텍스트 창의성 조정 0.7로함
    private double temperature;
    // 기본 생성자


    // Getters and Setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public List<OpenaiMessage> getOpenaiMessage() {
        return messages;
    }

    public void setMessages(List<OpenaiMessage> messages) {
        this.messages = messages;
    }
    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public static class OpenaiMessage {
        @JsonProperty("role") // 역할: user, assistant, system
        private String role;

        @JsonProperty("content") // 메시지 내용
        private String content;

        // 기본 생성자
        public OpenaiMessage() {}

        // role과 content를 초기화하는 생성자
        public OpenaiMessage(String role, String content) {
            this.role = role;
            this.content = content;
        }

        // Getters and Setters
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
