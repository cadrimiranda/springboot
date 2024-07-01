package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.Exercise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
    List<Exercise> findByNameContainingIgnoreCase(@Param("name") String name);

    Page<Exercise> findByNameContainingIgnoreCase(@Param("name") String name, @NonNull Pageable pageable);
}
