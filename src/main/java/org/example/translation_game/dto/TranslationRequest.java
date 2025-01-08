package org.example.translation_game.dto;

public class TranslationRequest {
    private String sourceText; // 원문
    private String userTranslation; // 사용자가 입력한 번역 (평가 요청 시 필요)

    public TranslationRequest(String sourceText, String userTranslation) {
        this.sourceText = sourceText;
        this.userTranslation = userTranslation;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getUserTranslation() {
        return userTranslation;
    }

    public void setUserTranslation(String userTranslation) {
        this.userTranslation = userTranslation;
    }
}
