package com.electoscope.backend.crawler;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsCrawlerTestRunner implements CommandLineRunner {

    private final NewsCrawlerService newsCrawlerService;

    @Override
    public void run(String... args) {
        newsCrawlerService.runCrawler();
    }
}
