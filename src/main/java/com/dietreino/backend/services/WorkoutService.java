package com.dietreino.backend.services;

import com.dietreino.backend.domain.ExerciseSet;
import com.dietreino.backend.domain.User;
import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetFullSetupDTO;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetRequestDTO;
import com.dietreino.backend.dto.exerciseSetup.ExerciseSetupFullDTO;
import com.dietreino.backend.dto.exerciseSetup.ExerciseSetupRequestDTO;
import com.dietreino.backend.dto.workout.WorkoutPutRequestDTO;
import com.dietreino.backend.dto.workout.WorkoutRequestDTO;
import com.dietreino.backend.repositories.WorkoutRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
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
    private final UserService userService;
    private final List<String> fields = List.of("Name", "Description");

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, ExerciseSetService exerciseSetService,
                          ExerciseSetupService exerciseSetupService, UserService userService) {
        this.workoutRepository = workoutRepository;
        this.exerciseSetService = exerciseSetService;
        this.exerciseSetupService = exerciseSetupService;
        this.userService = userService;
    }

    @Override
    public Workout convertDto(WorkoutRequestDTO dto) {
        validateDto(dto);
        return Workout.builder()
                .name(dto.name())
                .description(dto.description())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .exerciseSets(new ArrayList<>())
                .build();
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

    public Workout save(WorkoutRequestDTO workoutRequestDTO, UUID userRequestId) {
        User creationUser = userService.getDomainUser(userRequestId);
        Workout workout = convertDto(workoutRequestDTO);
        workout.setCreatedBy(creationUser);
        Workout savedWorkout = workoutRepository.save(workout);

        this.userService.addActiveWorkout(workoutRequestDTO.userToAssign(), savedWorkout);
        return savedWorkout;
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
        return null;
    }

    public Workout update(UUID id, WorkoutPutRequestDTO workoutRequestDTO) {
        Workout workout = findById(id);
        workout.setName(workoutRequestDTO.name());
        workout.setDescription(workoutRequestDTO.description());
        workout.setEndDate(workoutRequestDTO.endDate());
        return workoutRepository.save(workout);
    }

    @Override
    public List<Workout> findByCriteria(Map<String, String> criteria) {
        return findByFilter(workoutRepository, criteria, fields, Workout.class);
    }

    @Override
    public void delete(UUID id) {
        userService.removeUserWorkout(id);
        workoutRepository.deleteById(id);
    }

    public Workout addSetToWorkout(UUID workoutID, ExerciseSetFullSetupDTO setDto) {
        Workout workout = findById(workoutID);
        ExerciseSetRequestDTO exerciseSetRequestDTO =
                ExerciseSetRequestDTO
                        .builder()
                        .name(setDto.name())
                        .description(setDto.description())
                        .weekDay(setDto.weekDay())
                        .build();

        ExerciseSet exerciseSet = exerciseSetService.save(exerciseSetRequestDTO);
        exerciseSet.setExerciseSetupList(new ArrayList<>());

        for (ExerciseSetupFullDTO setupDto : setDto.exerciseSetupList()) {
            exerciseSet.getExerciseSetupList().add(exerciseSetupService.save(
                    ExerciseSetupRequestDTO
                            .builder()
                            .exerciseId(setupDto.exerciseId())
                            .series(setupDto.series())
                            .repetitions(setupDto.repetitions())
                            .rest(setupDto.rest())
                            .observation(setupDto.observation())
                            .build()
            ));
        }

        workout.getExerciseSets().add(exerciseSet);
        return workoutRepository.save(workout);
    }
}
