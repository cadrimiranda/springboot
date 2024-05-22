package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.User;
import com.dietreino.backend.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u.activeWorkout FROM User u WHERE u.id = :userId")
    Optional<Workout> findActiveWorkoutByUserId(@Param("userId") UUID userId);

    @Query("SELECT u from User u where u.activeWorkout IS NOT NULL and u.planExpiration > CURRENT_DATE")
    List<User> findActiveUsersWithActiveWorkoutAndPlanActive();
}
