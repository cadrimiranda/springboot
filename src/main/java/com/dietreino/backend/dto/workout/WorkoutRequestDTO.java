package com.dietreino.backend.dto.workout;

import java.util.Date;
import java.util.UUID;

public record WorkoutRequestDTO(String name, String description, UUID userToAssign, Date startDate, Date endDate) {
}
