package com.electoscope.backend.crawler;

import com.electoscope.backend.service.SentimentService;
import com.electoscope.backend.util.HuggingFaceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsCrawlerService {

    private final HuggingFaceClient huggingFaceClient;
    private final SentimentService sentimentService;

    public void runCrawler() {
        NewsListParser listParser = new NewsListParser();
        List<NewsItem> items = listParser.fetchNewsList();

        for (NewsItem item : items) {
            String content = NewsDetailParser.extractContent(item.getUrl());

            if (content.length() < 100) continue; // 본문 너무 짧은 뉴스 제외

            String summary = huggingFaceClient.summarizeText(content);
            String sentiment = sentimentService.getSentimentLabel(summary);

            log.info("📰 [{}] {}", item.getPress(), item.getTitle());
            log.info("📄 요약: {}", summary);
            log.info("🧠 감성: {}", sentiment);
            log.info("🔗 링크: {}", item.getUrl());
        }
    }
}
