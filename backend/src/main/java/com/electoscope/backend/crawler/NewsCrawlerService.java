package com.electoscope.backend.crawler;

import com.electoscope.backend.news.service.NewsSummaryService;
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
    private final NewsSummaryService newsSummaryService;

    public void runCrawler() {
        NewsListParser listParser = new NewsListParser();
        List<NewsItem> items = listParser.fetchNewsList();

        for (NewsItem item : items) {
            String url = item.getUrl();

            if (newsSummaryService.existsByUrl(url)) {
                log.info("⚠️ 이미 저장된 뉴스입니다 → {}", url);
                continue;
            }

            String content = NewsDetailParser.extractContent(url);
            if (content.length() < 100) continue;

            String summary = huggingFaceClient.summarizeText(content);
            String sentiment = sentimentClient.getSentiment(summary);

            log.info("📰 [{}] {}", item.getPress(), item.getTitle());
            log.info("📄 요약: {}", summary);
            log.info("🧠 감성: {}", sentiment);
            log.info("🔗 링크: {}", url);

            // TODO: 뉴스 저장 로직 추가 필요 시 여기에
        }
    }
}
