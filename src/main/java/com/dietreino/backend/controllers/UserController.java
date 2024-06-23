package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.user.UserEditDTO;
import com.dietreino.backend.dto.user.UserResponse;
import com.dietreino.backend.dto.user.UsersByTrainerDTO;
import com.dietreino.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId) {
        UserResponse user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/active-workout")
    public ResponseEntity<Workout> getActiveWorkout(@PathVariable UUID userId) {
        Workout workout = userService.getActiveWorkout(userId);
        return ResponseEntity.ok(workout);
    }

    @GetMapping("/all-by/personal-trainer")
    public ResponseEntity<UsersByTrainerDTO> getPersonalTrainers(
            @RequestParam(name = "trainerId") UUID personalTrainerId,
            Pageable pageable) {
        return ResponseEntity.ok(userService.getUsersByPersonalTrainer(personalTrainerId, pageable));
    }

    @PutMapping("/deactive/{userId}")
    public ResponseEntity<Boolean> deactiveUser(@PathVariable UUID userId) {
        userService.updateUserActive(userId, false);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/active/{userId}")
    public ResponseEntity<Boolean> activeUser(@PathVariable UUID userId) {
        userService.updateUserActive(userId, true);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Boolean> updateUser(@PathVariable UUID userId, @RequestBody UserEditDTO user) {
        userService.edit(userId, user);
        return ResponseEntity.ok(true);
    }
}
