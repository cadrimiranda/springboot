package com.dietreino.backend.services;

import com.dietreino.backend.domain.ExerciseSet;
import com.dietreino.backend.domain.ExerciseSetup;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetFullSetupDTO;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetRequestDTO;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetSetupDTO;
import com.dietreino.backend.exceptions.GenericException;
import com.dietreino.backend.repositories.ExerciseSetRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ExerciseSetService extends CRUDService<ExerciseSet, ExerciseSetRequestDTO> {
    private final ExerciseSetupService exerciseSetupService;
    private final ExerciseSetRepository exerciseSetRepository;
    private final List<String> fields = List.of("Name", "Description");

    @Autowired
    public ExerciseSetService(ExerciseSetupService exerciseSetupService, ExerciseSetRepository exerciseSetRepository) {
        this.exerciseSetupService = exerciseSetupService;
        this.exerciseSetRepository = exerciseSetRepository;
    }

    @Override
    public ExerciseSet convertDto(ExerciseSetRequestDTO exerciseSetRequestDTO) {
        validateDto(exerciseSetRequestDTO);
        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setName(exerciseSetRequestDTO.name());
        exerciseSet.setDescription(exerciseSetRequestDTO.description());

        return exerciseSet;
    }

    public ExerciseSet convertFullDTO(ExerciseSetFullSetupDTO dto) {
        ExerciseSet exerciseSet = exerciseSetRepository.findById(dto.id()).orElse(new ExerciseSet());
        exerciseSet.setName(dto.name());
        exerciseSet.setDescription(dto.description());
        List<ExerciseSetup> exerciseSetups = exerciseSetupService.convertFullDtoList(dto.exerciseSetupList());
        exerciseSet.setExerciseSetupList(exerciseSetups);
        return exerciseSet;
    }

    @Transactional
    public ExerciseSet updateFullDTO(ExerciseSetFullSetupDTO dto) {
        ExerciseSet exerciseSet = this.convertFullDTO(dto);
        return exerciseSetRepository.save(exerciseSet);
    }

    @Override
    public void validateDto(ExerciseSetRequestDTO exerciseSetRequestDTO) {
        if (exerciseSetRequestDTO == null) {
            throw new IllegalArgumentException("ExerciseSetRequestDTO cannot be null");
        }
        if (exerciseSetRequestDTO.name() == null || exerciseSetRequestDTO.name().length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long and not null");
        }
        if (exerciseSetRequestDTO.description() == null || exerciseSetRequestDTO.description().length() < 5) {
            throw new IllegalArgumentException("Description must be at least 5 characters long and not null");
        }
    }

    @Override
    public ExerciseSet save(ExerciseSetRequestDTO t) {
        try {
            return exerciseSetRepository.save(convertDto(t));
        } catch (IllegalArgumentException ex) {
            throw new GenericException("Error while saving Exercise Set: " + t, ex.getCause());
        } catch (Exception e) {
            throw new GenericException("Unexpected error while saving Exercise Set: " + t, e.getCause());
        }
    }

    @Override
    public List<ExerciseSet> findAll() {
        try {
            return exerciseSetRepository.findAll();
        } catch (Exception e) {
            throw new GenericException("Unexpected error occurred while fetching all ExerciseSets", e);
        }
    }

    @Override
    public ExerciseSet findById(UUID id) {
        return exerciseSetRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Exercise set with id " + id + " not found")
                );
    }

    @Override
    public ExerciseSet update(UUID id, ExerciseSetRequestDTO exerciseSetRequestDTO) {
        ExerciseSet exerciseSet = findById(id);
        exerciseSet.setName(exerciseSetRequestDTO.name());
        exerciseSet.setDescription(exerciseSetRequestDTO.description());
        exerciseSetRepository.save(exerciseSet);
        return exerciseSet;
    }

    @Override
    public void delete(UUID id) {
        try {
            exerciseSetRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new GenericException("Invalid ID: " + id, e.getCause());
        } catch (EntityNotFoundException enf) {
            throw new GenericException("ExerciseSet with id " + id + " not found", enf.getCause());
        } catch (Exception e) {
            throw new GenericException("Unexpected error occurred while deleting the ExerciseSet with id " + id, e.getCause());
        }
    }

    @Override
    public List<ExerciseSet> findByCriteria(Map<String, String> criteria) {
        return findByFilter(exerciseSetRepository, criteria, fields, ExerciseSet.class);
    }

    public ExerciseSet addExerciseSetupToSet(ExerciseSetSetupDTO dto) {
        List<UUID> exerciseSetupId = dto.exerciseSetupId();
        List<ExerciseSetup> setups = exerciseSetupService.findAllIds(exerciseSetupId);

        return exerciseSetRepository
                .findById(dto.exerciseSetId())
                .map(set -> {
                    setups.forEach(setup -> set.getExerciseSetupList().add(setup));
                    return exerciseSetRepository.save(set);
                }).orElseThrow(() ->
                        new EntityNotFoundException("Exercise setup with id " + exerciseSetupId + " not found")
                );
    }

    public List<ExerciseSet> findAllIds(List<UUID> exerciseSetIds) {
        return exerciseSetRepository.findAllById(exerciseSetIds);
    }
}
