package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.User;
import com.dietreino.backend.domain.Workout;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u.activeWorkout FROM User u WHERE u.id = :userId")
    Optional<Workout> findActiveWorkoutByUserId(@Param("userId") UUID userId);

    Page<User> findAllByPersonalTrainerId(UUID trainerId, Pageable pageable);

    Optional<User> findUserByActiveWorkoutId(UUID activeWorkoutId);
}
