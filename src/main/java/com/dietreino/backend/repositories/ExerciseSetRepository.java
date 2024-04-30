package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, UUID> {
}
