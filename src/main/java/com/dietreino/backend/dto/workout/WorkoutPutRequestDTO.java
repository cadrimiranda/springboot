package com.dietreino.backend.dto.workout;

import java.util.Date;

public record WorkoutPutRequestDTO(String name, String description, Date endDate) {
}
