package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.ExerciseSet;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetRequestDTO;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetSetupDTO;
import com.dietreino.backend.services.ExerciseSetService;
import com.dietreino.backend.utils.CRUDController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exerciseset")
public class ExerciseSetController extends CRUDController<ExerciseSet, ExerciseSetRequestDTO> {
    private final ExerciseSetService service;

    @Autowired
    public ExerciseSetController(ExerciseSetService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/byId/{id}")
    public ResponseEntity<ExerciseSet> getById(@PathVariable UUID id) {
        ExerciseSet eSet = service.findById(id);
        return ResponseEntity.ok(eSet);
    }

    @Override
    @PostMapping
    public ResponseEntity<ExerciseSet> create(@RequestBody ExerciseSetRequestDTO exerciseSetRequestDTO) {
        ExerciseSet exerciseSet = service.save(exerciseSetRequestDTO);
        return ResponseEntity.ok(exerciseSet);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseSet> update(@PathVariable UUID id, @RequestBody ExerciseSetRequestDTO exerciseSetRequestDTO) {
        ExerciseSet exerciseSet = service.update(id, exerciseSetRequestDTO);
        return ResponseEntity.ok(exerciseSet);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted exercise set with id: " + id);
    }

    @Override
    @GetMapping("/getall")
    public ResponseEntity<List<ExerciseSet>> getAll() {
        List<ExerciseSet> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add/exercises")
    public ResponseEntity<ExerciseSet> addExerciseSet(@RequestBody ExerciseSetSetupDTO dto) {
        ExerciseSet set = service.addExerciseSetupToSet(dto);
        return ResponseEntity.ok(set);
    }
}
