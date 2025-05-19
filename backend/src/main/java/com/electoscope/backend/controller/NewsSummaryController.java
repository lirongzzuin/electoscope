package com.electoscope.backend.controller;

import com.electoscope.backend.news.dto.NewsSummaryResponseDTO;
import com.electoscope.backend.news.service.NewsSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news-summaries")
@RequiredArgsConstructor
public class NewsSummaryController {

    private final NewsSummaryService newsSummaryService;

    @GetMapping
    public List<NewsSummaryResponseDTO> getAllSummaries() {
        return newsSummaryService.getAll();
    }
}
