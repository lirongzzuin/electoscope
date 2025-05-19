package com.electoscope.backend.crawler;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NewsListParser {

    private static final String NAVER_NEWS_URL = "https://news.naver.com/election/president2025/news";

    public List<NewsItem> fetchNewsList() {
        List<NewsItem> result = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(NAVER_NEWS_URL).get();
            Elements articles = doc.select(".section_list_area ul li");

            for (Element li : articles) {
                String title = li.selectFirst(".sa_text_title a") != null ?
                        li.selectFirst(".sa_text_title a").text() : "제목 없음";

                String url = li.selectFirst(".sa_text_title a") != null ?
                        li.selectFirst(".sa_text_title a").absUrl("href") : "";

                String press = li.selectFirst(".sa_text_press") != null ?
                        li.selectFirst(".sa_text_press").text() : "언론사 없음";

                String date = li.selectFirst(".sa_text_datetime") != null ?
                        li.selectFirst(".sa_text_datetime").text() : "시간 없음";

                result.add(new NewsItem(title, url, press, date));
            }

        } catch (Exception e) {
            log.error("네이버 뉴스 목록 크롤링 실패", e);
        }

        return result;
    }
}
