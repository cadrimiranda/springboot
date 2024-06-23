package com.dietreino.backend.dto.user;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserListActivePlanWorkout(UUID id, String name, String nextAppointment, String planExpiration,
                                        String planStart, String workoutExpiration, Boolean active) {
}
