package com.electoscope.backend.service;

import com.electoscope.backend.dto.SummaryRequestDTO;
import com.electoscope.backend.dto.SummaryResponseDTO;
import com.electoscope.backend.util.HuggingFaceClient;
import com.electoscope.backend.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final HuggingFaceClient huggingFaceClient;
    private final RedisUtil redisUtil;
    private final SentimentService sentimentService;

    public SummaryResponseDTO summarize(SummaryRequestDTO request) {
        String key = "summary:" + request.getText().hashCode();

        // 1. 캐시가 존재하면 바로 반환
        if (redisUtil.hasKey(key)) {
            String cached = redisUtil.get(key);
            return new SummaryResponseDTO(request.getText(), cached, "캐시된 응답 (감성 분석 생략)");
        }

        // 2. HuggingFace로 요약 요청
        String summary = huggingFaceClient.summarizeText(request.getText());

        // 3. 요약 결과에 대해 감성 분석 수행
        String sentiment = sentimentService.getSentimentLabel(summary);

        // 4. Redis에 요약 결과만 캐싱 (감성은 실시간 처리)
        redisUtil.set(key, summary, 3600);

        // 5. 결과 리턴
        return new SummaryResponseDTO(request.getText(), summary, sentiment);
    }
}
