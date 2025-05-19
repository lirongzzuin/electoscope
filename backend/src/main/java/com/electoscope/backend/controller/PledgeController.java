package com.electoscope.backend.controller;

import com.electoscope.backend.dto.PledgeDTO;
import com.electoscope.backend.dto.PledgeRequestDTO;
import com.electoscope.backend.service.PledgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pledges")
@RequiredArgsConstructor
public class PledgeController {

    private final PledgeService pledgeService;

    @GetMapping
    public List<PledgeDTO> getAll() {
        return pledgeService.getAll();
    }

    @PostMapping
    public void add(@RequestBody PledgeRequestDTO request) {
        pledgeService.save(request);
    }
}
