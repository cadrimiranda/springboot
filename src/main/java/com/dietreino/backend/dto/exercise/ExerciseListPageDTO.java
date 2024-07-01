package com.dietreino.backend.dto.exercise;

import com.dietreino.backend.domain.Exercise;
import com.dietreino.backend.dto.PageableDTO;
import com.dietreino.backend.utils.GroupMuscularEnum;
import org.springframework.data.domain.Page;

import java.util.List;

public class ExerciseListPageDTO extends PageableDTO<Exercise, ExerciseDTO> {
    public ExerciseListPageDTO(Page<Exercise> page) {
        super(page);
    }

    @Override
    public void transformContent(List<Exercise> content) {
        this.items = content.stream().map(exercise ->
                ExerciseDTO.builder()
                        .id(exercise.getId())
                        .name(exercise.getName())
                        .image(exercise.getImage())
                        .url(exercise.getUrl())
                        .description(exercise.getDescription())
                        .muscularGroup(GroupMuscularEnum.valueOf(exercise.getMuscularGroup().getName()))
                        .build()
        ).toList();
    }
}
