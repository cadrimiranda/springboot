package com.dietreino.backend.services;

import com.dietreino.backend.domain.Exercise;
import com.dietreino.backend.dto.exercise.ExerciseAutocompleteDTO;
import com.dietreino.backend.dto.exercise.ExerciseRequestDTO;
import com.dietreino.backend.repositories.ExerciseRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExerciseService extends CRUDService<Exercise, ExerciseRequestDTO> {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

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
        return exerciseRepository.findByNameContainingIgnoreCase(criteria.get("name"));
    }

    @Override
    public void delete(UUID id) {
        Exercise exercise = this.findById(id);
        exerciseRepository.delete(exercise);
    }

    public List<ExerciseAutocompleteDTO> autocomplete(String name) {
        return exerciseRepository.findByNameContainingIgnoreCase(name).stream().map((exercise) ->
                new ExerciseAutocompleteDTO(exercise.getName(), exercise.getId())
        ).collect(Collectors.toList());
    }
}
