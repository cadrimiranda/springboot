package com.dietreino.backend.dto.exercise;

import com.dietreino.backend.utils.GroupMuscularEnum;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ExerciseDTO(UUID id, String name, String description, String url, String image,
                          GroupMuscularEnum muscularGroup) {
}
