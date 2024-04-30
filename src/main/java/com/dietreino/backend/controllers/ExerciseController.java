package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.Exercise;
import com.dietreino.backend.dto.exercise.ExerciseRequestDTO;
import com.dietreino.backend.services.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercise")
@AllArgsConstructor
public class ExerciseController {
    private final ExerciseService service;

    @GetMapping("/getone/{id}")
    public Exercise getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    public Exercise create(@RequestBody ExerciseRequestDTO body) {
        return service.save(body);
    }

    @PostMapping("/getall")
    public List<Exercise> getAll() {
        return service.findAll();
    }

    @DeleteMapping
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public Exercise update(@PathVariable UUID id, @RequestBody ExerciseRequestDTO body) {
        return service.update(id, body);
    }
}
