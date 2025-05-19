package com.electoscope.backend.crawler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsItem {
    private String title;
    private String url;
    private String press;
    private String datetime;
}
