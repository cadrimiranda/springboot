package com.dietreino.backend.services;

import com.dietreino.backend.domain.Exercise;
import com.dietreino.backend.domain.ExerciseSetup;
import com.dietreino.backend.dto.ExerciseSetupRequestDTO;
import com.dietreino.backend.repositories.ExerciseSetupRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ExerciseSetupService extends CRUDService<ExerciseSetup, ExerciseSetupRequestDTO> {
    private final ExerciseSetupRepository repository;
    private final ExerciseService exerciseService;

    @Override
    public ExerciseSetup convertDto(ExerciseSetupRequestDTO dto) {
        ExerciseSetup exerciseSetup = new ExerciseSetup();
        exerciseSetup.setSeries(dto.series());
        exerciseSetup.setRepetitions(dto.repetitions());
        exerciseSetup.setRest(dto.rest());
        exerciseSetup.setObservation(dto.observation());
        return exerciseSetup;
    }

    @Override
    public void validateDto(ExerciseSetupRequestDTO dto) {
        if(dto.exerciseId().toString().isEmpty()) {
            throw new IllegalArgumentException("Exercise ID cannot be empty");
        }

        if (dto.series().isEmpty()) {
            throw new IllegalArgumentException("Series cannot be empty");
        }

        if (Long.parseLong(dto.series()) < 1) {
            throw new IllegalArgumentException("Series must be greater than 0");
        }

        if (dto.rest().isEmpty()) {
            throw new IllegalArgumentException("Rest cannot be empty");
        }

        if(dto.repetitions().isEmpty()) {
            throw new IllegalArgumentException("Repetitions cannot be empty");
        }
    }

    @Override
    public ExerciseSetup save(ExerciseSetupRequestDTO dto) {
        validateDto(dto);
        Exercise exercise = exerciseService.findById(dto.exerciseId());
        ExerciseSetup exerciseSetup = convertDto(dto);
        exerciseSetup.setExercise(exercise);

        return repository.save(exerciseSetup);
    }

    @Override
    public List<ExerciseSetup> findAll() {
        return repository.findAll();
    }

    @Override
    public ExerciseSetup findById(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Muscular group with id " + id + " not found")
        );
    }

    @Override
    public ExerciseSetup update(UUID id, ExerciseSetupRequestDTO exerciseSetupRequestDTO) {
        ExerciseSetup exerciseSetup = findById(id);
        validateDto(exerciseSetupRequestDTO);
        exerciseSetup.setSeries(exerciseSetupRequestDTO.series());
        exerciseSetup.setRepetitions(exerciseSetupRequestDTO.repetitions());
        exerciseSetup.setRest(exerciseSetupRequestDTO.rest());
        exerciseSetup.setObservation(exerciseSetupRequestDTO.observation());
        return repository.save(exerciseSetup);
    }

    @Override
    public List<ExerciseSetup> findByCriteria(Map<String, Object> criteria) {
        return repository.findByExerciseNameLike(criteria.get("name").toString());
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<ExerciseSetup> findAllIds(List<UUID> ids) {
        return repository.findAllById(ids);
    }
}
