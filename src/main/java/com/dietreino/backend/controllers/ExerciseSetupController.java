package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.ExerciseSetup;
import com.dietreino.backend.dto.ExerciseSetupRequestDTO;
import com.dietreino.backend.services.ExerciseSetupService;
import com.dietreino.backend.utils.CRUDController;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ExerciseSetup getById(@PathVariable UUID id){
        return service.findById(id);
    }

    @Override
    @GetMapping("/getall")
    public List<ExerciseSetup> getAll() {
        return service.findAll();
    }

    @Override
    @PostMapping("/save")
    public ExerciseSetup create(@RequestBody ExerciseSetupRequestDTO dto) {
        return service.save(dto);
    }

    @Override
    @PutMapping("/{id}")
    public ExerciseSetup update(@PathVariable UUID id, @RequestBody ExerciseSetupRequestDTO dto) {
        return service.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id) {
        service.delete(id);
    }
}
