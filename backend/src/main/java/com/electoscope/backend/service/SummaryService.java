package com.electoscope.backend.service;

import com.electoscope.backend.dto.SummaryRequestDTO;
import com.electoscope.backend.dto.SummaryResponseDTO;
import com.electoscope.backend.util.HuggingFaceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final HuggingFaceClient huggingFaceClient;

    public SummaryResponseDTO summarize(SummaryRequestDTO request) {
        String summary = huggingFaceClient.summarizeText(request.getText());
        return new SummaryResponseDTO(request.getText(), summary);
    }
}
