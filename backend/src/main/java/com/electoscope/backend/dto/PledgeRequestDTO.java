package com.electoscope.backend.dto;

import lombok.Data;

@Data
public class PledgeRequestDTO {
    private String candidate;
    private String category;
    private String content;
}
