package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.AnthropometricReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AnthropometricReportRepository extends JpaRepository<AnthropometricReport, UUID> {
    List<AnthropometricReport> findByUserId(UUID userId);
}
