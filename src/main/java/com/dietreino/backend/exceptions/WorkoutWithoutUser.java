package com.dietreino.backend.exceptions;

import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class WorkoutWithoutUser extends EntityNotFoundException {
    public WorkoutWithoutUser(UUID workoutId) {
        super("The workout is not active for any user. Workout id: " + workoutId);
    }
}
