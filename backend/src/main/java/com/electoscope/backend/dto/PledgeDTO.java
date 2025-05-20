package com.electoscope.backend.dto;

import com.electoscope.backend.entity.Pledge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PledgeDTO {

    private String candidate;
    private String category;
    private String content;
    private String sentiment;

    public static PledgeDTO from(Pledge entity) {
        return new PledgeDTO(
                entity.getCandidate(),
                entity.getCategory(),
                entity.getContent(),
                entity.getSentiment()
        );
    }
}
