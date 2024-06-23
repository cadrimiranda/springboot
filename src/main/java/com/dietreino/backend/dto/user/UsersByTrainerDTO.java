package com.dietreino.backend.dto.user;

import com.dietreino.backend.domain.User;
import com.dietreino.backend.dto.PageableDTO;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UsersByTrainerDTO extends PageableDTO<User, UserListActivePlanWorkout> {
    public UsersByTrainerDTO(Page<User> page) {
        super(page);
    }

    @Override
    public void transformContent(List<User> content) {
        this.items = content.stream().map(user ->
                UserListActivePlanWorkout
                        .builder()
                        .planExpiration(Optional.ofNullable(user.getPlanExpiration()).map(Date::toString).orElse(null))
                        .planStart(Optional.ofNullable(user.getPlanStart()).map(Date::toString).orElse(null))
                        .workoutExpiration(Optional.ofNullable(user.getActiveWorkout()).map(workout -> workout.getEndDate().toString()).orElse(null))
                        .nextAppointment(Optional.ofNullable(user.getNextAppointment()).map(Date::toString).orElse(null))
                        .id(user.getId())
                        .name(user.getName())
                        .active(user.getActive())
                        .build()
        ).toList();
    }
}
