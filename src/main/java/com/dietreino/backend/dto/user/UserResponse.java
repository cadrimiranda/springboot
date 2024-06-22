package com.dietreino.backend.dto.user;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
        UUID id,
        UUID activeWorkoutId,
        String name,
        String lastName,
        String fullName,
        String email,
        String phone,
        String birthDate,
        String nextAppointment,
        String planExpiration,
        String planStart,
        String workoutExpiration) {
}
