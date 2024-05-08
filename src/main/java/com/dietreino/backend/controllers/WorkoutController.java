package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.WorkoutRequestDTO;
import com.dietreino.backend.dto.WorkoutSetRequestDTO;
import com.dietreino.backend.services.WorkoutService;
import com.dietreino.backend.utils.CRUDController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workout")
public class WorkoutController extends CRUDController<Workout, WorkoutRequestDTO> {
    private final WorkoutService service;

    @Autowired
    public WorkoutController(WorkoutService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/byId/{id}")
    public ResponseEntity<Workout> getById(@PathVariable UUID id) {
        Workout workout = service.findById(id);
        return ResponseEntity.ok(workout);
    }

    @Override
    @PostMapping
    public ResponseEntity<Workout> create(@RequestBody WorkoutRequestDTO workoutRequestDTO) {
        Workout workout = service.save(workoutRequestDTO);
        return ResponseEntity.ok(workout);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Workout> update(@PathVariable UUID id, @RequestBody WorkoutRequestDTO workoutRequestDTO) {
        Workout workout = service.update(id, workoutRequestDTO);
        return ResponseEntity.ok(workout);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted workout with id: " + id);
    }

    @Override
    @GetMapping("/getall")
    public ResponseEntity<List<Workout>> getAll() {
        List<Workout> workouts = service.findAll();
        return ResponseEntity.ok(workouts);
    }


    @PostMapping("/exerciseset")
    public ResponseEntity<Workout> addExerciseSet(@RequestBody WorkoutSetRequestDTO dto) {
        Workout workout = service.addSetToWorkout(dto);
        return ResponseEntity.ok(workout);
    }
}
