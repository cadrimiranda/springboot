package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, UUID> {
}
