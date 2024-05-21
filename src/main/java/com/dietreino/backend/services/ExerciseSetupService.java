package com.dietreino.backend.services;

import com.dietreino.backend.domain.Exercise;
import com.dietreino.backend.domain.ExerciseSetup;
import com.dietreino.backend.dto.exerciseSetup.ExerciseSetupFullDTO;
import com.dietreino.backend.dto.exerciseSetup.ExerciseSetupRequestDTO;
import com.dietreino.backend.exceptions.IdCannotBeNullWhileFinding;
import com.dietreino.backend.repositories.ExerciseSetupRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ExerciseSetupService extends CRUDService<ExerciseSetup, ExerciseSetupRequestDTO> {
    private final ExerciseSetupRepository repository;
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseSetupService(ExerciseSetupRepository repository, ExerciseService exerciseService) {
        this.repository = repository;
        this.exerciseService = exerciseService;
    }

    @Override
    public ExerciseSetup convertDto(ExerciseSetupRequestDTO dto) {
        ExerciseSetup exerciseSetup = new ExerciseSetup();
        exerciseSetup.setSeries(dto.series());
        exerciseSetup.setRepetitions(dto.repetitions());
        exerciseSetup.setRest(dto.rest());
        exerciseSetup.setObservation(dto.observation());
        return exerciseSetup;
    }

    public List<ExerciseSetup> convertFullDtoList(List<ExerciseSetupFullDTO> dtoList) {
        List<ExerciseSetup> exerciseSetups = new ArrayList<>();
        for (ExerciseSetupFullDTO dto : dtoList) {
            try {
                UUID setupId = dto.id();
                ExerciseSetup exerciseSetup = setupId == null ? new ExerciseSetup() : this.findById(dto.id());
                exerciseSetup.setSeries(dto.series());
                exerciseSetup.setRepetitions(dto.repetitions());
                exerciseSetup.setRest(dto.rest());
                exerciseSetup.setObservation(dto.observation());
                if (setupId == null) {
                    exerciseSetup = this.repository.save(exerciseSetup);
                }
                exerciseSetup.setExercise(exerciseService.findById(dto.exerciseId()));

                exerciseSetups.add(exerciseSetup);
            } catch (InvalidDataAccessApiUsageException exception) {
                throw new IdCannotBeNullWhileFinding("SetupId cannot be null while fetching exercise setup " + dto.series() + " " + dto.repetitions() + " " + dto.rest());
            }
        }

        return exerciseSetups;
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
                new EntityNotFoundException("Exercise setup with id " + id + " not found")
        );
    }

    @Override
    public ExerciseSetup update(UUID id, ExerciseSetupRequestDTO exerciseSetupRequestDTO) {
        if (id == null) {
            return this.save(exerciseSetupRequestDTO);
        }

        ExerciseSetup exerciseSetup = findById(id);
        validateDto(exerciseSetupRequestDTO);
        exerciseSetup.setSeries(exerciseSetupRequestDTO.series());
        exerciseSetup.setRepetitions(exerciseSetupRequestDTO.repetitions());
        exerciseSetup.setRest(exerciseSetupRequestDTO.rest());
        exerciseSetup.setObservation(exerciseSetupRequestDTO.observation());

        Exercise exercise = exerciseService.findById(exerciseSetupRequestDTO.exerciseId());
        exerciseSetup.setExercise(exercise);

        return repository.save(exerciseSetup);
    }

    @Override
    public List<ExerciseSetup> findByCriteria(Map<String, String> criteria) {
        return repository.findByExerciseNameLike(criteria.get("name"));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public List<ExerciseSetup> findAllIds(List<UUID> ids) {
        return repository.findAllById(ids);
    }
}
