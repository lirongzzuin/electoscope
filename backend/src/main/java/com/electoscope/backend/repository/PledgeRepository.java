package com.electoscope.backend.repository;

import com.electoscope.backend.entity.Pledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PledgeRepository extends JpaRepository<Pledge, Long> {
}
