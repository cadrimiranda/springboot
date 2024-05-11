package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.ExerciseSetup;
import com.dietreino.backend.dto.exerciseSetup.ExerciseSetupRequestDTO;
import com.dietreino.backend.services.ExerciseSetupService;
import com.dietreino.backend.utils.CRUDController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercisesetup")
public class ExerciseSetupController extends CRUDController<ExerciseSetup, ExerciseSetupRequestDTO> {
    private final ExerciseSetupService service;

    @Autowired
    public ExerciseSetupController(ExerciseSetupService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/getone/{id}")
    public ResponseEntity<ExerciseSetup> getById(@PathVariable UUID id){
        ExerciseSetup exerciseSetup = service.findById(id);
        return ResponseEntity.ok(exerciseSetup);
    }

    @Override
    @GetMapping("/getall")
    public ResponseEntity<List<ExerciseSetup>> getAll() {
        List<ExerciseSetup> exerciseSetups = service.findAll();
        return ResponseEntity.ok(exerciseSetups);
    }

    @Override
    @PostMapping("/save")
    public ResponseEntity<ExerciseSetup> create(@RequestBody ExerciseSetupRequestDTO dto) {
        ExerciseSetup exerciseSetup = service.save(dto);
        return ResponseEntity.ok(exerciseSetup);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseSetup> update(@PathVariable UUID id, @RequestBody ExerciseSetupRequestDTO dto) {
        ExerciseSetup exerciseSetup = service.update(id, dto);
        return ResponseEntity.ok(exerciseSetup);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted exercise setup with id: " + id);
    }
}
