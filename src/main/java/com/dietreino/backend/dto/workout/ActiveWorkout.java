package com.dietreino.backend.dto.workout;

import com.dietreino.backend.domain.Workout;
import lombok.Builder;

@Builder
public record ActiveWorkout(Workout workout) {
}
