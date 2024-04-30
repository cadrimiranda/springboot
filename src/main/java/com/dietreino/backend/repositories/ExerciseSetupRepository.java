package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.ExerciseSetup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExerciseSetupRepository extends JpaRepository<ExerciseSetup, UUID> {
    List<ExerciseSetup> findByExerciseNameLike(String name);
}
