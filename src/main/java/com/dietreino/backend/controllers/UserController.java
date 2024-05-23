package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.user.UserListActivePlanWorkout;
import com.dietreino.backend.dto.user.UserResponse;
import com.dietreino.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @GetMapping("/active-plan-workout")
    public ResponseEntity<List<UserListActivePlanWorkout>> ListUsers() {
        List<UserListActivePlanWorkout> users = userService.getUsersWithActivePlanAndWorkout();
        return ResponseEntity.ok(users);
    }
}
