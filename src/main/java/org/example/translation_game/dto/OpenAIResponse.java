package org.example.translation_game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public class OpenAIResponse {

    @JsonProperty("choices")
    private List<Choice> choices; // choices 라는 리스트 선언

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {
        @JsonProperty("text")
        private String text; // 응답 항목에서 생성된 텍스트 데이터를 text에 저장

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
