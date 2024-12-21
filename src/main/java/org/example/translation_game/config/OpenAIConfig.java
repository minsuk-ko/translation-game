package org.example.translation_game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig { // Openai api 를 사용하기 위한 구성

    @Bean
    public RestTemplate restTemplate(){ // http 클라이언트 템플릿 빈등록
        return new RestTemplate();
    }
}
