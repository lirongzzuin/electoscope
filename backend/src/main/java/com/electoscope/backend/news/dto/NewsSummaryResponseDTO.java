package com.electoscope.backend.news.dto;

import com.electoscope.backend.news.entity.NewsSummary;
import lombok.Getter;

@Getter
public class NewsSummaryResponseDTO {

    private final String title;
    private final String summary;
    private final String sentiment;
    private final String press;
    private final String url;
    private final String dateTime;

    public NewsSummaryResponseDTO(NewsSummary entity) {
        this.title = entity.getTitle();
        this.summary = entity.getSummary();
        this.sentiment = entity.getSentiment();
        this.press = entity.getPress();
        this.url = entity.getUrl();
        this.dateTime = entity.getDateTime();
    }
}
