package com.dietreino.backend.services;

import com.dietreino.backend.domain.ExerciseSet;
import com.dietreino.backend.domain.ExerciseSetup;
import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetFullSetupDTO;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetRequestDTO;
import com.dietreino.backend.dto.exerciseSetup.ExerciseSetupFullDTO;
import com.dietreino.backend.dto.exerciseSetup.ExerciseSetupRequestDTO;
import com.dietreino.backend.dto.workout.WorkoutRequestDTO;
import com.dietreino.backend.repositories.WorkoutRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class WorkoutService extends CRUDService<Workout, WorkoutRequestDTO> {
    private final ExerciseSetService exerciseSetService;
    private final WorkoutRepository workoutRepository;
    private final ExerciseSetupService exerciseSetupService;
    private final List<String> fields = List.of("Name", "Description");

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, ExerciseSetService exerciseSetService, ExerciseSetupService exerciseSetupService) {
        this.workoutRepository = workoutRepository;
        this.exerciseSetService = exerciseSetService;
        this.exerciseSetupService = exerciseSetupService;
    }

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

    @Transactional
    public Workout addSetToWorkout(UUID workoutID, ExerciseSetFullSetupDTO setDto) {
        Workout workout = findById(workoutID);
        ExerciseSetRequestDTO exerciseSetRequestDTO = new ExerciseSetRequestDTO(setDto.name(), setDto.description());
        ExerciseSet exerciseSet = exerciseSetService.save(exerciseSetRequestDTO);
        exerciseSet.setExerciseSetupList(new ArrayList<>());
        for (ExerciseSetupFullDTO setupDto : setDto.exerciseSetupList()) {
            ExerciseSetupRequestDTO exerciseSetupRequestDTO = new ExerciseSetupRequestDTO(setupDto.exerciseId(), setupDto.series(), setupDto.repetitions(), setupDto.rest(), setupDto.observation());
            ExerciseSetup exerciseSetup = exerciseSetupService.save(exerciseSetupRequestDTO);
            exerciseSet.getExerciseSetupList().add(exerciseSetup);
        }
        workout.getExerciseSets().add(exerciseSet);
        return workoutRepository.save(workout);
    }
}
