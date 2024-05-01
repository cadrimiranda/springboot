package com.dietreino.backend.utils;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class CRUDService<T, DTO> {
    public abstract T convertDto(DTO dto);
    public abstract void validateDto(DTO dto);
    public abstract T save(DTO t);
    public abstract List<T> findAll();
    public abstract T findById(UUID id);
    public abstract T update(UUID id, DTO dto);
    public abstract List<T> findByCriteria(Map<String, String> criteria);
    public abstract void delete(UUID id);

    protected List<T> findByFilter(JpaRepository<T, ?> repository, Map<String, String> criteria, List<String> fields, Class<T> clazz) {
        Map<String, String> acceptedCriteria = new HashMap<>();
        fields.stream()
                .filter(criteria::containsKey)
                .forEach(field -> acceptedCriteria.put(field, criteria.get(field)));

        T probe;

        try {
            probe = clazz.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, String> entry : acceptedCriteria.entrySet()) {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(probe, entry.getValue()); // Type casting might be needed
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create probe: ", e);
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<T> example = Example.of(probe, matcher);
        return repository.findAll(example);
    }
}
