package com.electoscope.backend.service;

import com.electoscope.backend.dto.PledgeDTO;
import com.electoscope.backend.dto.PledgeRequestDTO;
import com.electoscope.backend.entity.Pledge;
import com.electoscope.backend.repository.PledgeRepository;
import com.electoscope.backend.util.HuggingFaceSentimentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PledgeService {

    private final PledgeRepository pledgeRepository;
    private final HuggingFaceSentimentClient huggingFaceSentimentClient;

    public List<PledgeDTO> getAll() {
        return pledgeRepository.findAll().stream()
                .map(PledgeDTO::from)
                .toList();
    }

    public void save(PledgeRequestDTO request) {
        String sentiment = huggingFaceSentimentClient.getSentiment(request.getContent());

        Pledge entity = Pledge.builder()
                .candidate(request.getCandidate())
                .category(request.getCategory())
                .content(request.getContent())
                .sentiment(sentiment)
                .build();

        pledgeRepository.save(entity);
    }

}
