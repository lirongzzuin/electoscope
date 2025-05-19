package com.electoscope.backend.service;

import com.electoscope.backend.util.HuggingFaceClient;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentimentService {

    private final HuggingFaceClient huggingFaceClient;

    public String getSentimentLabel(String text) {
        String rawJson = huggingFaceClient.analyzeSentiment(text);

        try {
            JSONArray predictions = new JSONArray(rawJson);
            JSONArray scores = predictions.getJSONArray(0);

            String bestLabel = "Unknown";
            double maxScore = -1;

            for (int i = 0; i < scores.length(); i++) {
                JSONObject obj = scores.getJSONObject(i);
                double score = obj.getDouble("score");
                if (score > maxScore) {
                    maxScore = score;
                    bestLabel = obj.getString("label");
                }
            }

            return bestLabel; // 예: "4 stars"
        } catch (Exception e) {
            return "감성 분석 실패";
        }
    }
}
