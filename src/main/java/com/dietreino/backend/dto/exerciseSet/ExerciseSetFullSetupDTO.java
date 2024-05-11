package com.dietreino.backend.dto.exerciseSet;

import com.dietreino.backend.dto.exerciseSetup.ExerciseSetupFullDTO;

import java.util.List;
import java.util.UUID;

public record ExerciseSetFullSetupDTO(UUID id, String name, String description,
                                      List<ExerciseSetupFullDTO> exerciseSetupList) {
}
