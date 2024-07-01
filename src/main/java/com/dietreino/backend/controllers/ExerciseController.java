package com.dietreino.backend.controllers;

import com.dietreino.backend.dto.exercise.ExerciseAutocompleteDTO;
import com.dietreino.backend.dto.exercise.ExerciseDTO;
import com.dietreino.backend.dto.exercise.ExerciseListPageDTO;
import com.dietreino.backend.services.ExerciseService;
import com.dietreino.backend.utils.CRUDController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercise")
public class ExerciseController extends CRUDController<ExerciseDTO, ExerciseDTO, ExerciseDTO> {
    private final ExerciseService service;

    @Autowired
    public ExerciseController(ExerciseService service) {
        this.service = service;
    }

    @GetMapping("/byId/{id}")
    @Override
    public ResponseEntity<ExerciseDTO> getById(@PathVariable UUID id) {
        ExerciseDTO exercise = service.findById(id);
        return ResponseEntity.ok(exercise);
    }

    @GetMapping("/autocomplete/{name}")
    public ResponseEntity<List<ExerciseAutocompleteDTO>> autoComplete(@PathVariable String name) {
        List<ExerciseAutocompleteDTO> exercises = service.autocomplete(name);
        return ResponseEntity.ok(exercises);
    }

    @PostMapping
    @Override
    public ResponseEntity<ExerciseDTO> create(@Valid @RequestBody ExerciseDTO body) {
        ExerciseDTO exercise = service.save(body);
        return ResponseEntity.ok(exercise);
    }

    @GetMapping("/getall")
    public ResponseEntity<ExerciseListPageDTO> getAll(
            @RequestParam(name = "name", defaultValue = "") String name,
            Pageable pageable) {
        return ResponseEntity.ok(service.findAll(name, pageable));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted exercise with id: " + id);
    }

    @Override
    public ResponseEntity<List<ExerciseDTO>> getAll() {
        return null;
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDTO> update(@PathVariable UUID id, @Valid @RequestBody ExerciseDTO body) {
        ExerciseDTO exercise = service.update(id, body);
        return ResponseEntity.ok(exercise);
    }
}
