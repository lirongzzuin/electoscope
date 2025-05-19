package com.electoscope.backend.news.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 5000)
    private String content;

    @Column(length = 2000)
    private String summary;

    private String sentiment;
    private String press;
    private String url;
    private String dateTime;
}
