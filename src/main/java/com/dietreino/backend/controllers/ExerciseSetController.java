package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.ExerciseSet;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetRequestDTO;
import com.dietreino.backend.dto.exerciseSet.ExerciseSetSetupDTO;
import com.dietreino.backend.services.ExerciseSetService;
import com.dietreino.backend.utils.CRUDController;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/getone/{id}")
    public ExerciseSet getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @Override
    @PostMapping
    public ExerciseSet create(@RequestBody ExerciseSetRequestDTO exerciseSetRequestDTO) {
        return service.save(exerciseSetRequestDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ExerciseSet update(@PathVariable UUID id, @RequestBody ExerciseSetRequestDTO exerciseSetRequestDTO) {
        return service.update(id, exerciseSetRequestDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id) {
        service.delete(id);
    }

    @Override
    @PostMapping("/getall")
    public List<ExerciseSet> getAll() {
        return service.findAll();
    }

    @PostMapping("/add/exercises")
    public ExerciseSet addExerciseSet(@RequestBody ExerciseSetSetupDTO dto) {
        return service.addExerciseSetupToSet(dto);
    }
}
