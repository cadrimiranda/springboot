package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.ExerciseSetup;
import com.dietreino.backend.dto.ExerciseSetupRequestDTO;
import com.dietreino.backend.services.ExerciseSetupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/exercisesetup")
@AllArgsConstructor
public class ExerciseSetupController {
    private final ExerciseSetupService service;

    @GetMapping("/getone/{id}")
    public ExerciseSetup getOne(@PathVariable UUID id){
        return service.findById(id);
    }

    @GetMapping("/getall")
    public List<ExerciseSetup> getAllExerciseSetups() {
        return service.findAll();
    }

    @PostMapping("/save")
    public ExerciseSetup saveExerciseSetup(@RequestBody ExerciseSetupRequestDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public ExerciseSetup updateExerciseSetup(@PathVariable UUID id, @RequestBody ExerciseSetupRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteExerciseSetup(@PathVariable UUID id) {
        service.delete(id);
    }
}
