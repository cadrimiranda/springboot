package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, UUID> {
    List<ExerciseSet> findByNameContainingOrDescriptionContaining(String name, String desc);
    List<ExerciseSet> findByNameContaining(String name);
    List<ExerciseSet> findByDescriptionContaining(String desc);
}
