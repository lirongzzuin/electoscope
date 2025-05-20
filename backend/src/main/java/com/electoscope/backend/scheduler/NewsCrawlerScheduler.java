package com.electoscope.backend.scheduler;

import com.electoscope.backend.crawler.NewsCrawlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewsCrawlerScheduler {

    private final NewsCrawlerService newsCrawlerService;

    @Scheduled(cron = "0 0 6,12,18 * * *") // 하루 3번: 6시, 12시, 18시
    public void runScheduledCrawling() {
        log.info("🕒 뉴스 자동 크롤링 시작");
        newsCrawlerService.runCrawler();
        log.info("✅ 뉴스 자동 크롤링 완료");
    }
}
