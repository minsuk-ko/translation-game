package org.example.translation_game.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.translation_game.dto.OpenAiRequest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class JsonParserExample {
    public static OpenAiRequest parseJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("request.json");
        OpenAiRequest request = objectMapper.readValue(resource.getFile(),OpenAiRequest.class);
        return request;

    }
    public static void main(String[] args){
        try {
            OpenAiRequest request = parseJson();
            System.out.println("model : " + request.getModel());
            System.out.println("messages " + request.getOpenaiMessage().get(0).getContent());
        }
        catch(IOException e){
            e.printStackTrace();

        }
    }
}
