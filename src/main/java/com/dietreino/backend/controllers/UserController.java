package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.user.UserListActivePlanWorkout;
import com.dietreino.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{userId}/active-workout")
    public ResponseEntity<Workout> getActiveWorkout(@PathVariable UUID userId) {
        Optional<Workout> workout = userService.getActiveWorkout(userId);
        return workout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/active-plan-workout")
    public ResponseEntity<List<UserListActivePlanWorkout>> ListUsers() {
        List<UserListActivePlanWorkout> users = userService.getUsersWithActivePlanAndWorkout();
        return ResponseEntity.ok(users);
    }
}
