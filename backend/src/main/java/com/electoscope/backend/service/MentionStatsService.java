package com.electoscope.backend.service;

import com.electoscope.backend.news.entity.NewsSummary;
import com.electoscope.backend.news.repository.NewsSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MentionStatsService {

    private final NewsSummaryRepository newsSummaryRepository;

    private static final List<String> CANDIDATES = List.of("이재명", "김문수", "이준석");

    public Map<String, Integer> getMentionCounts() {
        List<String> summaries = newsSummaryRepository.findAll()
                .stream()
                .map(n -> n.getTitle() + " " + n.getSummary())
                .toList();

        Map<String, Integer> result = new HashMap<>();

        for (String candidate : CANDIDATES) {
            int count = 0;
            for (String s : summaries) {
                count += countOccurrences(s, candidate);
            }
            result.put(candidate, count);
        }

        return result.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a, LinkedHashMap::new
                ));
    }

    private int countOccurrences(String text, String keyword) {
        return text.split(keyword, -1).length - 1;
    }

    public Map<String, Map<String, Integer>> getDailyMentionCounts() {
        List<NewsSummary> allNews = newsSummaryRepository.findAll();

        Map<String, Map<String, Integer>> result = new HashMap<>();

        for (NewsSummary news : allNews) {
            String date = news.getDateTime().split(" ")[0]; // YYYY-MM-DD 기준
            String content = news.getTitle() + " " + news.getSummary();

            for (String candidate : CANDIDATES) {
                if (!result.containsKey(candidate)) {
                    result.put(candidate, new HashMap<>());
                }

                int count = countOccurrences(content, candidate);
                result.get(candidate).merge(date, count, Integer::sum);
            }
        }

        // 날짜 정렬
        for (Map<String, Integer> dateMap : result.values()) {
            result.replaceAll((k, v) ->
                    v.entrySet().stream()
                            .sorted(Map.Entry.comparingByKey())
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey, Map.Entry::getValue,
                                    (a, b) -> a, LinkedHashMap::new
                            ))
            );
        }

        return result;
    }

}
