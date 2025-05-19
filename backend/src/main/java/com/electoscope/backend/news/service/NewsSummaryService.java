package com.electoscope.backend.news.service;

import com.electoscope.backend.news.dto.NewsSummaryResponseDTO;
import com.electoscope.backend.news.entity.NewsSummary;
import com.electoscope.backend.news.repository.NewsSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsSummaryService {

    private final NewsSummaryRepository newsSummaryRepository;

    public void save(NewsSummary summary) {
        if (!newsSummaryRepository.existsByUrl(summary.getUrl())) {
            newsSummaryRepository.save(summary);
        }
    }

    public List<NewsSummaryResponseDTO> getAll() {
        return newsSummaryRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .map(NewsSummaryResponseDTO::new)
                .toList();
    }
}
