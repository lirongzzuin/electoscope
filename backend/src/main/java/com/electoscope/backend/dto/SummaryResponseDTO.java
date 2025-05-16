package com.electoscope.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SummaryResponseDTO {
    private String originalText;
    private String summary;
}
