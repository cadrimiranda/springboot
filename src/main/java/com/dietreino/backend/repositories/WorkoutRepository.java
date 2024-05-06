package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, UUID> {
    @Query("SELECT w FROM Workout w JOIN w.exerciseSets e WHERE e.id = :exerciseSetId")
    List<Workout> findAllByExerciseSetId(@Param("exerciseSetId") UUID exerciseSetId);
}
