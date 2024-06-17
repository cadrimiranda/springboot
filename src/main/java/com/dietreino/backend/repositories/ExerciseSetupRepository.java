package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.ExerciseSetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExerciseSetupRepository extends JpaRepository<ExerciseSetup, UUID> {
    List<ExerciseSetup> findByExerciseNameLike(String name);

    List<ExerciseSetup> findAllByExerciseId(UUID id);
}
