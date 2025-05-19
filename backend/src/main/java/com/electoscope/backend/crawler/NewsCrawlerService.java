package com.electoscope.backend.crawler;

import com.electoscope.backend.util.HuggingFaceClient;
import com.electoscope.backend.util.HuggingFaceSentimentClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsCrawlerService {

    private final HuggingFaceClient huggingFaceClient;
    private final HuggingFaceSentimentClient sentimentClient;

    public void runCrawler() {
        NewsListParser listParser = new NewsListParser();
        List<NewsItem> items = listParser.fetchNewsList();

        for (NewsItem item : items) {
            String content = NewsDetailParser.extractContent(item.getUrl());

            if (content.length() < 100) continue;

            String summary = huggingFaceClient.summarizeText(content);
            String sentiment = sentimentClient.getSentiment(summary);

            log.info("📰 [{}] {}", item.getPress(), item.getTitle());
            log.info("📄 요약: {}", summary);
            log.info("🧠 감성: {}", sentiment);
            log.info("🔗 링크: {}", item.getUrl());
        }
    }
}
