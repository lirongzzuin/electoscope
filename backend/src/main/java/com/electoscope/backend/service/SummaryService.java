package com.electoscope.backend.service;

import com.electoscope.backend.dto.SummaryRequestDTO;
import com.electoscope.backend.dto.SummaryResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    public SummaryResponseDTO summarize(SummaryRequestDTO request) {
        // TODO: HuggingFace 연동 전, 임시 반환
        String dummySummary = "이것은 예시 요약 결과입니다.";
        return new SummaryResponseDTO(request.getText(), dummySummary);
    }
}
