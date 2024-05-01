package com.dietreino.backend.services;

import com.dietreino.backend.domain.ExerciseSet;
import com.dietreino.backend.domain.ExerciseSetup;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetRequestDTO;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetSetupDTO;
import com.dietreino.backend.exceptions.GenericException;
import com.dietreino.backend.repositories.ExerciseSetRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExerciseSetService extends CRUDService<ExerciseSet, ExerciseSetRequestDTO> {
    private ExerciseSetupService exerciseSetupService;
    private ExerciseSetRepository exerciseSetRepository;

    @Override
    public ExerciseSet convertDto(ExerciseSetRequestDTO exerciseSetRequestDTO) {
        validateDto(exerciseSetRequestDTO);
        ExerciseSet exerciseSet = new ExerciseSet();
        exerciseSet.setName(exerciseSetRequestDTO.name());
        exerciseSet.setDescription(exerciseSetRequestDTO.description());

        return exerciseSet;
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
        return null;
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
    public List<ExerciseSet> findByCriteria(Map<String, Object> criteria) {
        final String name = (String) criteria.get("Name");
        final String description = (String) criteria.get("Description");

        if (name != null && description != null) {
            return exerciseSetRepository.findByNameContainingOrDescriptionContaining(name, description);
        } else if (name != null) {
            return exerciseSetRepository.findByNameContaining(name);
        } else if (description != null) {
            return exerciseSetRepository.findByDescriptionContaining(description);
        }
        return List.of();
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
}
