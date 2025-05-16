package com.electoscope.backend.controller;

import com.electoscope.backend.dto.SummaryRequestDTO;
import com.electoscope.backend.dto.SummaryResponseDTO;
import com.electoscope.backend.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/summarize")
@RequiredArgsConstructor
public class SummaryController {

    private final SummaryService summaryService;

    @PostMapping
    public SummaryResponseDTO summarize(@RequestBody SummaryRequestDTO request) {
        return summaryService.summarize(request);
    }
}
