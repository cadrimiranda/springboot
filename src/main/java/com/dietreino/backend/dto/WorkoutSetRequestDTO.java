package com.dietreino.backend.dto;

import java.util.List;
import java.util.UUID;

public record WorkoutSetRequestDTO(UUID workoutId, List<UUID> setsId) {
}
