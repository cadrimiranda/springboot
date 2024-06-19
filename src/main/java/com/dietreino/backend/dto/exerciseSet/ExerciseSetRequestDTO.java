package com.dietreino.backend.dto.exerciseSet;

import com.dietreino.backend.utils.WeekDays;
import lombok.Builder;

@Builder
public record ExerciseSetRequestDTO(String name, String description, WeekDays day) {
}
