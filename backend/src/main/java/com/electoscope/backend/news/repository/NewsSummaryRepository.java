package com.electoscope.backend.news.repository;

import com.electoscope.backend.news.entity.NewsSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsSummaryRepository extends JpaRepository<NewsSummary, Long> {
    boolean existsByUrl(String url);
}
