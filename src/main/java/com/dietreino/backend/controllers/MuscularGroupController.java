package com.dietreino.backend.controllers;


import com.dietreino.backend.domain.MuscularGroup;
import com.dietreino.backend.dto.MuscularGroup.MuscularGroupRequestDTO;
import com.dietreino.backend.services.MuscularGroupService;
import com.dietreino.backend.utils.CRUDController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/musculargroup")
@AllArgsConstructor
public class MuscularGroupController extends CRUDController<MuscularGroup, MuscularGroupRequestDTO> {
    private final MuscularGroupService muscularGroupService;

    @GetMapping("/getone/{id}")
    @Override
    public MuscularGroup getById(@PathVariable UUID id) {
        return muscularGroupService.findById(id);
    }

    @PostMapping("/save")
    @Override
    public MuscularGroup create(@RequestBody MuscularGroupRequestDTO body) {
        return muscularGroupService.save(body);
    }

    @PostMapping("/getall")
    @Override
    public List<MuscularGroup> getAll() {
        return muscularGroupService.findAll();
    }

    @DeleteMapping
    @Override
    public void deleteOne(@PathVariable UUID id) {
        muscularGroupService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public MuscularGroup update(@PathVariable UUID id, @RequestBody MuscularGroupRequestDTO body) {
        return muscularGroupService.update(id, body);
    }
}
