package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.Exercise;
import com.dietreino.backend.dto.exercise.ExerciseRequestDTO;
import com.dietreino.backend.services.ExerciseService;
import com.dietreino.backend.utils.CRUDController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercise")
@AllArgsConstructor
public class ExerciseController extends CRUDController<Exercise, ExerciseRequestDTO> {
    private final ExerciseService service;

    @GetMapping("/getone/{id}")
    @Override
    public Exercise getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PostMapping
    @Override
    public Exercise create(@Valid @RequestBody ExerciseRequestDTO body) {
        return service.save(body);
    }

    @Override
    @PostMapping("/getall")
    public List<Exercise> getAll() {
        return service.findAll();
    }

    @Override
    @DeleteMapping
    public void deleteOne(@PathVariable UUID id) {
        service.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Exercise update(@PathVariable UUID id, @Valid @RequestBody ExerciseRequestDTO body) {
        return service.update(id, body);
    }
}
