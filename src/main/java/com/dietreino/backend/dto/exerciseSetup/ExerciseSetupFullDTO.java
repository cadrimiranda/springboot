package com.dietreino.backend.dto.exerciseSetup;

import java.util.UUID;

public record ExerciseSetupFullDTO(UUID id, UUID exerciseId, String series, String repetitions, String rest,
                                   String observation) {
}
