package com.dietreino.backend.dto.exerciseSetup;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ExerciseSetupRequestDTO(UUID exerciseId, String series, String repetitions, String rest, String observation) {
}
