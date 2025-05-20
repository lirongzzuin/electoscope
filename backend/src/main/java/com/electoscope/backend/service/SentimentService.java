package com.electoscope.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SentimentService {

    @Value("${HUGGINGFACE_API_KEY}")
    private String apiKey;

    @Value("${HUGGINGFACE_SENTIMENT_URL}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getSentimentLabel(String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = Map.of("inputs", content);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
            JSONArray json = new JSONArray(response.getBody());
            String label = json.getJSONObject(0).getString("label");

            return switch (label) {
                case "POSITIVE" -> "긍정";
                case "NEGATIVE" -> "부정";
                case "NEUTRAL" -> "중립";
                default -> "분석 실패";
            };
        } catch (Exception e) {
            log.error("감성 분석 실패: {}", e.getMessage());
            return "분석 실패";
        }
    }
}
