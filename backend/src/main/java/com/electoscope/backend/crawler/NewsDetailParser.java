package com.electoscope.backend.crawler;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Slf4j
public class NewsDetailParser {

    public static String extractContent(String url) {
        try {
            Document doc = Jsoup.connect(url).get();

            // 네이버 뉴스 본문은 id="dic_area"에 위치함
            Elements content = doc.select("#dic_area");

            return content.text();
        } catch (Exception e) {
            log.error("본문 크롤링 실패: {}", url, e);
            return "";
        }
    }
}
