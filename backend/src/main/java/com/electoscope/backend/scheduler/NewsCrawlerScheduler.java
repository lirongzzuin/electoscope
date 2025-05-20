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

    @Scheduled(cron = "0 0 * * * *") // 매 1시간마다 정각에 실행
    public void runScheduledCrawling() {
        log.info("🕒 뉴스 자동 크롤링 시작");
        newsCrawlerService.runCrawler();
        log.info("✅ 뉴스 자동 크롤링 완료");
    }
}
