package com.dietreino.backend.dto;

import java.util.UUID;

public record ExerciseSetupRequestDTO(UUID exerciseId, String series, String repetitions, String rest, String observation) {
}
