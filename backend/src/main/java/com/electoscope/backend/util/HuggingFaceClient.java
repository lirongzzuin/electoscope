package com.electoscope.backend.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class HuggingFaceClient {

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Value("${huggingface.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String summarizeText(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("inputs", text);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("요약 실패: {}", e.getMessage());
            return "요약에 실패했습니다.";
        }
    }

    // ⭐ 감성 분석 추가 메서드
    public String analyzeSentiment(String text) {
        String sentimentModelUrl = "https://api-inference.huggingface.co/models/nlptown/bert-base-multilingual-uncased-sentiment";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("inputs", text);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(sentimentModelUrl, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("감성 분석 실패: {}", e.getMessage());
            return "감성 분석 실패";
        }
    }
}
