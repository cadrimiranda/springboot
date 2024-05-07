package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.Exercise;
import com.dietreino.backend.dto.exercise.ExerciseRequestDTO;
import com.dietreino.backend.services.ExerciseService;
import com.dietreino.backend.utils.CRUDController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercise")
public class ExerciseController extends CRUDController<Exercise, ExerciseRequestDTO> {
    private final ExerciseService service;

    @Autowired
    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @GetMapping("/byId/{id}")
    @Override
    public ResponseEntity<Exercise> getById(@PathVariable UUID id) {
        Exercise exercise = service.findById(id);
        return ResponseEntity.ok(exercise);
    }

    @PostMapping
    @Override
    public ResponseEntity<Exercise> create(@Valid @RequestBody ExerciseRequestDTO body) {
        Exercise exercise = service.save(body);
        return ResponseEntity.ok(exercise);
    }

    @Override
    @PostMapping("/getall")
    public ResponseEntity<List<Exercise>> getAll() {
        List<Exercise> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<String> deleteOne(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted exercise with id: " + id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Exercise> update(@PathVariable UUID id, @Valid @RequestBody ExerciseRequestDTO body) {
        Exercise exercise = service.update(id, body);
        return ResponseEntity.ok(exercise);
    }
}
