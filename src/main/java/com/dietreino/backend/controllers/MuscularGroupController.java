package com.dietreino.backend.controllers;


import com.dietreino.backend.domain.MuscularGroup;
import com.dietreino.backend.dto.MuscularGroup.MuscularGroupRequestDTO;
import com.dietreino.backend.services.MuscularGroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/musculargroup")
@AllArgsConstructor
public class MuscularGroupController {
    private final MuscularGroupService muscularGroupService;

    @GetMapping("/getone/{id}")
    public MuscularGroup getById(@PathVariable UUID id) {
        return muscularGroupService.findById(id);
    }

    @PostMapping("/save")
    public MuscularGroup create(@RequestBody MuscularGroupRequestDTO body) {
        return muscularGroupService.save(body);
    }

    @PostMapping("/getall")
    public List<MuscularGroup> getAll() {
        return muscularGroupService.findAll();
    }

    @DeleteMapping
    public void delete(@PathVariable UUID id) {
        muscularGroupService.delete(id);
    }

    @PutMapping("/{id}")
    public MuscularGroup update(@PathVariable UUID id, @RequestBody MuscularGroupRequestDTO body) {
        return muscularGroupService.update(id, body);
    }
}
