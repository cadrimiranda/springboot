package com.dietreino.backend.services;

import com.dietreino.backend.domain.AnthropometricReport;
import com.dietreino.backend.dto.AnthropometricReportRequestDTO;
import com.dietreino.backend.exceptions.GenericException;
import com.dietreino.backend.repositories.AnthropometricReportRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AnthropometricReportService extends CRUDService<AnthropometricReport, AnthropometricReportRequestDTO> {
    private final AnthropometricReportRepository reportRepository;

    @Autowired
    public AnthropometricReportService(AnthropometricReportRepository repository) {
        this.reportRepository = repository;
    }

    @Override
    public AnthropometricReport convertDto(AnthropometricReportRequestDTO dto) {
        return AnthropometricReport.builder()
                .reportDate(dto.reportDate())
                .height(dto.height())
                .weight(dto.weight())
                .idealWeight(dto.idealWeight())
                .bmi(dto.bmi())
                .bodyFatMass(dto.bodyFatMass())
                .percentageBodyFat(dto.percentageBodyFat())
                .leanMass(dto.leanMass())
                .percentageLeanMass(dto.percentageLeanMass())
                .bodyDensity(dto.bodyDensity())
                .sumOfSkinfolds(dto.sumOfSkinfolds())
                .armMuscleArea(dto.armMuscleArea())
                .armFatArea(dto.armFatArea())
                .shoulderCircumference(dto.shoulderCircumference())
                .chestCircumference(dto.chestCircumference())
                .waistCircumference(dto.waistCircumference())
                .abdomenCircumference(dto.abdomenCircumference())
                .rightCalfCircumference(dto.rightCalfCircumference())
                .leftCalfCircumference(dto.leftCalfCircumference())
                .rightThighCircumference(dto.rightThighCircumference())
                .leftThighCircumference(dto.leftThighCircumference())
                .relaxedRightArmCircumference(dto.relaxedRightArmCircumference())
                .relaxedLeftArmCircumference(dto.relaxedLeftArmCircumference())
                .contractedRightArmCircumference(dto.contractedRightArmCircumference())
                .contractedLeftArmCircumference(dto.contractedLeftArmCircumference())
                .rightForearmCircumference(dto.rightForearmCircumference())
                .leftForearmCircumference(dto.leftForearmCircumference())
                .tricepsSkinfold(dto.tricepsSkinfold())
                .midAxillarySkinfold(dto.midAxillarySkinfold())
                .chestSkinfold(dto.chestSkinfold())
                .abdominalSkinfold(dto.abdominalSkinfold())
                .suprailiacSkinfold(dto.suprailiacSkinfold())
                .subscapularSkinfold(dto.subscapularSkinfold())
                .thighSkinfold(dto.thighSkinfold())
                .build();
    }

    @Override
    public void validateDto(AnthropometricReportRequestDTO anthropometricReportRequestDTO) {
    }

    @Override
    public AnthropometricReport save(AnthropometricReportRequestDTO t) {
        return reportRepository.save(convertDto(t));
    }

    @Override
    public List<AnthropometricReport> findAll() {
        return List.of();
    }

    public List<AnthropometricReport> findAll(UUID userId) {
        return reportRepository.findByUserId(userId);
    }

    @Override
    public AnthropometricReport findById(UUID id) {
        return reportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Report with id " + id + " not found"));
    }

    @Override
    public AnthropometricReport update(UUID id, AnthropometricReportRequestDTO dto) {
        AnthropometricReport report = findById(id);

        report.setReportDate(dto.reportDate());
        report.setHeight(dto.height());
        report.setWeight(dto.weight());
        report.setIdealWeight(dto.idealWeight());
        report.setBmi(dto.bmi());
        report.setBodyFatMass(dto.bodyFatMass());
        report.setPercentageBodyFat(dto.percentageBodyFat());
        report.setLeanMass(dto.leanMass());
        report.setPercentageLeanMass(dto.percentageLeanMass());
        report.setBodyDensity(dto.bodyDensity());
        report.setSumOfSkinfolds(dto.sumOfSkinfolds());
        report.setArmMuscleArea(dto.armMuscleArea());
        report.setArmFatArea(dto.armFatArea());
        report.setShoulderCircumference(dto.shoulderCircumference());
        report.setChestCircumference(dto.chestCircumference());
        report.setWaistCircumference(dto.waistCircumference());
        report.setAbdomenCircumference(dto.abdomenCircumference());
        report.setRightCalfCircumference(dto.rightCalfCircumference());
        report.setLeftCalfCircumference(dto.leftCalfCircumference());
        report.setRightThighCircumference(dto.rightThighCircumference());
        report.setLeftThighCircumference(dto.leftThighCircumference());
        report.setRelaxedRightArmCircumference(dto.relaxedRightArmCircumference());
        report.setRelaxedLeftArmCircumference(dto.relaxedLeftArmCircumference());
        report.setContractedRightArmCircumference(dto.contractedRightArmCircumference());
        report.setContractedLeftArmCircumference(dto.contractedLeftArmCircumference());
        report.setRightForearmCircumference(dto.rightForearmCircumference());
        report.setLeftForearmCircumference(dto.leftForearmCircumference());
        report.setTricepsSkinfold(dto.tricepsSkinfold());
        report.setMidAxillarySkinfold(dto.midAxillarySkinfold());
        report.setChestSkinfold(dto.chestSkinfold());
        report.setAbdominalSkinfold(dto.abdominalSkinfold());
        report.setSuprailiacSkinfold(dto.suprailiacSkinfold());
        report.setSubscapularSkinfold(dto.subscapularSkinfold());
        report.setThighSkinfold(dto.thighSkinfold());

        return reportRepository.save(report);
    }

    @Override
    public List<AnthropometricReport> findByCriteria(Map<String, String> criteria) {
        return List.of();
    }

    @Override
    public void delete(UUID id) {
        try {
            this.findById(id);
            reportRepository.deleteById(id);
        } catch (Exception e) {
            // Handle potential data access or integrity issues
            throw new GenericException("Failed to delete the report: " + e.getMessage(), e);
        }
    }
}
