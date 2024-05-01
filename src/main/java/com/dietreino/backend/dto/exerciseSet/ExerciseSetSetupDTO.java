package com.dietreino.backend.dto.exerciseSet;

import java.util.List;
import java.util.UUID;

public record ExerciseSetSetupDTO(UUID exerciseSetId, List<UUID> exerciseSetupId) {
}
