package com.dietreino.backend.services;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class BaseService<T, DTO> {
    public abstract T convertDto(DTO dto);
    public abstract void validateDto(DTO dto);
    public abstract T save(DTO t);
    public abstract List<T> findAll();
    public abstract T findById(UUID id);
    public abstract T update(UUID id, DTO dto);
    public abstract List<T> findByCriteria(Map<String, Object> criteria);
    public abstract void delete(UUID id);
}
