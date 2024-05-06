package com.dietreino.backend.controllers;

import com.dietreino.backend.domain.Workout;
import com.dietreino.backend.dto.WorkoutRequestDTO;
import com.dietreino.backend.dto.WorkoutSetRequestDTO;
import com.dietreino.backend.services.WorkoutService;
import com.dietreino.backend.utils.CRUDController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/getone/{id}")
    public Workout getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @Override
    @PostMapping
    public Workout create(@RequestBody WorkoutRequestDTO workoutRequestDTO) {
        return service.save(workoutRequestDTO);
    }

    @Override
    @PutMapping("/{id}")
    public Workout update(@PathVariable UUID id, @RequestBody WorkoutRequestDTO workoutRequestDTO) {
        return service.update(id, workoutRequestDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable UUID id) {
        service.delete(id);
    }

    @Override
    @PostMapping("/getall")
    public List<Workout> getAll() {
        return service.findAll();
    }


    @PostMapping("/add/set")
    public Workout addExerciseSet(@RequestBody WorkoutSetRequestDTO dto) {
        return service.addSetToWorkout(dto);
    }
}
