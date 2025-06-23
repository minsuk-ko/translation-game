package org.example.translation_game.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Generate a random English sentence using OpenAI ChatGPT model.
     */
    public String generateSentence() {
        String prompt = "Generate a short random English sentence.";
        Map<String, Object> request = buildChatRequest(prompt);
        Map<?,?> response = postToOpenAI(request);
        return extractContent(response);
    }

    /**
     * Score the given Korean translation for the provided English sentence.
     * Returns an integer score between 0 and 100.
     */
    public int scoreTranslation(String english, String korean) {
        String prompt = "Score the quality of the following Korean translation " +
                "for the given English sentence on a scale of 0 to 100. " +
                "Respond with only the numeric score.\n" +
                "English: " + english + "\n" +
                "Korean: " + korean;
        Map<String, Object> request = buildChatRequest(prompt);
        Map<?,?> response = postToOpenAI(request);
        String content = extractContent(response);
        try {
            return Integer.parseInt(content.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private Map<String, Object> buildChatRequest(String prompt) {
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
        Map<String, Object> request = new HashMap<>();
        request.put("model", "gpt-3.5-turbo");
        request.put("messages", new Map[]{ message });
        return request;
    }

    @SuppressWarnings("unchecked")
    private Map<?,?> postToOpenAI(Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
        return restTemplate.postForObject("https://api.openai.com/v1/chat/completions", entity, Map.class);
    }

    @SuppressWarnings("unchecked")
    private String extractContent(Map<?,?> response) {
        if (response == null) return "";
        Object choices = response.get("choices");
        if (choices instanceof Iterable<?> iterable) {
            for (Object c : iterable) {
                if (c instanceof Map<?,?> map) {
                    Object message = map.get("message");
                    if (message instanceof Map<?,?> m) {
                        Object content = m.get("content");
                        if (content != null) return content.toString();
                    }
                }
            }
        }
        return "";
    }
}
