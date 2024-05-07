package com.dietreino.backend.controllers;


import com.dietreino.backend.domain.MuscularGroup;
import com.dietreino.backend.dto.MuscularGroup.MuscularGroupRequestDTO;
import com.dietreino.backend.services.MuscularGroupService;
import com.dietreino.backend.utils.CRUDController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/musculargroup")
public class MuscularGroupController extends CRUDController<MuscularGroup, MuscularGroupRequestDTO> {
    private final MuscularGroupService muscularGroupService;

    @Autowired
    public MuscularGroupController(MuscularGroupService muscularGroupService) {
        this.muscularGroupService = muscularGroupService;
    }

    @GetMapping("/byId/{id}")
    @Override
    public ResponseEntity<MuscularGroup> getById(@PathVariable UUID id) {
        MuscularGroup muscularGroup = muscularGroupService.findById(id);
        return ResponseEntity.ok(muscularGroup);
    }

    @PostMapping("/save")
    @Override
    public ResponseEntity<MuscularGroup> create(@RequestBody MuscularGroupRequestDTO body) {
        MuscularGroup muscularGroup = muscularGroupService.save(body);
        return ResponseEntity.ok(muscularGroup);
    }

    @PostMapping("/getall")
    @Override
    public ResponseEntity<List<MuscularGroup>> getAll() {
        List<MuscularGroup> muscularGroups = muscularGroupService.findAll();
        return ResponseEntity.ok(muscularGroups);
    }

    @DeleteMapping
    @Override
    public ResponseEntity<String> deleteOne(@PathVariable UUID id) {
        muscularGroupService.delete(id);
        return ResponseEntity.ok("Deleted muscular group with id: " + id);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MuscularGroup> update(@PathVariable UUID id, @RequestBody MuscularGroupRequestDTO body) {
        MuscularGroup muscularGroup = muscularGroupService.update(id, body);
        return ResponseEntity.ok(muscularGroup);
    }
}
