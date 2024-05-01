package com.dietreino.backend.services;

import com.dietreino.backend.domain.Exercise;
import com.dietreino.backend.dto.exercise.ExerciseRequestDTO;
import com.dietreino.backend.repositories.ExerciseRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExerciseService extends CRUDService<Exercise, ExerciseRequestDTO> {
    private final ExerciseRepository exerciseRepository;

    @Override
    public Exercise convertDto(ExerciseRequestDTO exerciseRequestDTO) {
        Exercise exercise = new Exercise();
        exercise.setName(exerciseRequestDTO.name());
        exercise.setDescription(exerciseRequestDTO.description());
        return exercise;
    }

    @Override
    public void validateDto(ExerciseRequestDTO exerciseRequestDTO) {
        if (exerciseRequestDTO.name().length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters");
        }
    }

    @Override
    public Exercise save(ExerciseRequestDTO t) {
        validateDto(t);
        Exercise exercise = convertDto(t);
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Exercise findById(UUID id) {
        return exerciseRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Exercise with id " + id + " not found")
                );
    }

    @Override
    public Exercise update(UUID id, ExerciseRequestDTO exerciseRequestDTO) {
        Exercise exercise = this.findById(id);
        exercise.setName(exerciseRequestDTO.name());
        exercise.setDescription(exerciseRequestDTO.description());
        return exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> findByCriteria(Map<String, String> criteria) {
        return exerciseRepository.findByNameLike(criteria.get("name"));
    }

    @Override
    public void delete(UUID id) {
        Exercise exercise = this.findById(id);
        exerciseRepository.delete(exercise);
    }
}
