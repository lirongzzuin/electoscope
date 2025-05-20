package com.electoscope.backend.util;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HuggingFaceSentimentClient {

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Value("${huggingface.sentiment.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getSentiment(String content) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(apiKey);
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, String> body = Map.of("inputs", content);
                HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

                ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
                JSONArray json = new JSONArray(response.getBody());
                String label = json.getJSONObject(0).getString("label");

                return switch (label) {
                    case "POSITIVE" -> "긍정";
                    case "NEGATIVE" -> "부정";
                    case "NEUTRAL" -> "중립";
                    default -> "중립";
                };
            } catch (Exception e) {
                if (attempt == 3) {
                    return "중립"; // 3회 실패 시 기본값
                }

                try {
                    Thread.sleep(1000L * attempt); // 재시도 전 점진적 대기
                } catch (InterruptedException ignored) {
                }
            }
        }

        return "중립";
    }
}
