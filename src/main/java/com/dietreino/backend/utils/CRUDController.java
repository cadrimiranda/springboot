package com.dietreino.backend.utils;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public abstract class CRUDController<Entity, RequestDTO> {
    public abstract Entity getById(@PathVariable UUID id);
    public abstract Entity create(@Valid @RequestBody RequestDTO dto);
    public abstract Entity update(@PathVariable UUID id, @Valid @RequestBody RequestDTO dto);
    public abstract void deleteOne(@PathVariable UUID id);
    public abstract List<Entity> getAll();
}
