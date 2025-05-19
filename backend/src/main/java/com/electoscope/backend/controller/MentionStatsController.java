package com.electoscope.backend.controller;

import com.electoscope.backend.service.MentionStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class MentionStatsController {

    private final MentionStatsService mentionStatsService;

    @GetMapping("/mentions")
    public Map<String, Integer> getMentionStats() {
        return mentionStatsService.getMentionCounts();
    }

    @GetMapping("/mentions/daily")
    public Map<String, Map<String, Integer>> getDailyMentionStats() {
        return mentionStatsService.getDailyMentionCounts();
    }

}
