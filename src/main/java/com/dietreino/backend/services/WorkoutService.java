package com.dietreino.backend.services;

import com.dietreino.backend.domain.ExerciseSet;
import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.WorkoutRequestDTO;
import com.dietreino.backend.dto.WorkoutSetRequestDTO;
import com.dietreino.backend.utils.CRUDService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.dietreino.backend.repositories.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WorkoutService extends CRUDService<Workout, WorkoutRequestDTO> {
    private final ExerciseSetService exerciseSetService;
    private WorkoutRepository workoutRepository;
    private final List<String> fields = List.of("Name", "Description");

    @Override
    public Workout convertDto(WorkoutRequestDTO workoutRequestDTO) {
        validateDto(workoutRequestDTO);
        Workout workout = new Workout();
        workout.setName(workoutRequestDTO.name());
        workout.setDescription(workoutRequestDTO.description());
        return workout;
    }

    @Override
    public void validateDto(WorkoutRequestDTO workoutRequestDTO) {
        if (workoutRequestDTO == null) {
            throw new IllegalArgumentException("ExerciseSetRequestDTO cannot be null");
        }
        if (workoutRequestDTO.name() == null || workoutRequestDTO.name().length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long and not null");
        }
        if (workoutRequestDTO.description() == null || workoutRequestDTO.description().length() < 5) {
            throw new IllegalArgumentException("Description must be at least 5 characters long and not null");
        }
    }

    @Override
    public Workout save(WorkoutRequestDTO workoutRequestDTO) {
        Workout workout = convertDto(workoutRequestDTO);
        return workoutRepository.save(workout);
    }

    @Override
    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    @Override
    public Workout findById(UUID id) {
        return workoutRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Workout with id " + id + " not found"));
    }

    @Override
    public Workout update(UUID id, WorkoutRequestDTO workoutRequestDTO) {
        Workout existingWorkout = findById(id);
        existingWorkout.setName(workoutRequestDTO.name());
        existingWorkout.setDescription(workoutRequestDTO.description());
        workoutRepository.save(existingWorkout);
        return existingWorkout;
    }

    @Override
    public List<Workout> findByCriteria(Map<String, String> criteria) {
        return findByFilter(workoutRepository, criteria, fields, Workout.class);
    }

    @Override
    public void delete(UUID id) {
        workoutRepository.deleteById(id);
    }

    public Workout addSetToWorkout(WorkoutSetRequestDTO dto) {
        List<UUID> setsId = dto.setsId();
        List<ExerciseSet> sets = exerciseSetService.findAllIds(setsId);

        return workoutRepository
                .findById(dto.workoutId())
                .map(workout -> {
                    sets.forEach(set -> workout.getExerciseSets().add(set));
                    return workoutRepository.save(workout);
                }).orElseThrow(() ->
                        new EntityNotFoundException("Workout with id " + dto.workoutId() + " not found")
                );
    }
}
