package com.dietreino.backend.utils;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public abstract class CRUDController<Entity, RequestDTO, PutDTO> {
    public abstract ResponseEntity<Entity> getById(@PathVariable UUID id);
    public abstract ResponseEntity<Entity> create(@Valid @RequestBody RequestDTO dto);

    public abstract ResponseEntity<Entity> update(@PathVariable UUID id, @Valid @RequestBody PutDTO dto);
    public abstract ResponseEntity<String> deleteOne(@PathVariable UUID id);
    public abstract ResponseEntity<List<Entity>> getAll();
}
