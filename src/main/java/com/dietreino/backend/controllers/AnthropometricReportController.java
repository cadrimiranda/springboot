package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.AnthropometricReport;
import com.dietreino.backend.dto.AnthropometricReportRequestDTO;
import com.dietreino.backend.services.AnthropometricReportService;
import com.dietreino.backend.utils.CRUDController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/anthropometricreport")
public class AnthropometricReportController extends CRUDController<AnthropometricReport, AnthropometricReportRequestDTO, AnthropometricReportRequestDTO> {
    private final AnthropometricReportService service;

    @Autowired
    public AnthropometricReportController(AnthropometricReportService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/byId/{id}")
    public ResponseEntity<AnthropometricReport> getById(@PathVariable UUID id) {
        AnthropometricReport report = service.findById(id);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<AnthropometricReport>> getAllByUserId(@PathVariable UUID id) {
        List<AnthropometricReport> reports = this.service.findAll(id);
        return ResponseEntity.ok(reports);
    }

    @Override
    @PostMapping
    public ResponseEntity<AnthropometricReport> create(@Valid @RequestBody AnthropometricReportRequestDTO anthropometricReportRequestDTO) {
        AnthropometricReport report = service.save(anthropometricReportRequestDTO);
        return ResponseEntity.ok(report);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<AnthropometricReport> update(@PathVariable UUID id, @Valid @RequestBody AnthropometricReportRequestDTO anthropometricReportRequestDTO) {
        AnthropometricReport report = service.update(id, anthropometricReportRequestDTO);
        return ResponseEntity.ok(report);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<AnthropometricReport>> getAll() {
        return null;
    }
}
