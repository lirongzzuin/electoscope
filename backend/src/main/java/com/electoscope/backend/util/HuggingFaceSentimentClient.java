package com.electoscope.backend.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HuggingFaceSentimentClient {

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Value("${huggingface.sentiment.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getSentiment(String content) {
        var headers = new org.springframework.http.HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        var body = Map.of("inputs", content);
        var entity = new org.springframework.http.HttpEntity<>(body, headers);

        try {
            var response = restTemplate.postForEntity(apiUrl, entity, String.class);
            var json = new JSONArray(response.getBody());
            var label = json.getJSONObject(0).getString("label");
            return switch (label) {
                case "POSITIVE" -> "긍정";
                case "NEGATIVE" -> "부정";
                case "NEUTRAL" -> "중립";
                default -> "분석 실패";
            };
        } catch (Exception e) {
            return "분석 실패";
        }
    }
}
