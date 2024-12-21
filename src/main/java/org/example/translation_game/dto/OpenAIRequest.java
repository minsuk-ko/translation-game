package org.example.translation_game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAIRequest { // openai api와 통신할 데이터를 정의
    @JsonProperty("model") // model 을 text-davinci-003으로 매핑
    private String model;

    @JsonProperty("prompt") // 매핑할 프롬포트
    private String prompt;

    @JsonProperty("max_tokens") // 최대 토큰수 150개 매핑
    private int maxTokens;

    @JsonProperty("temperature") // 생성 텍스트 창의성 조정 0.7로함
    private double temperature;

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
}
