package com.dietreino.backend.repositories;

import com.dietreino.backend.domain.MuscularGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MuscularGroupRepository extends JpaRepository<MuscularGroup, UUID> {
    List<MuscularGroup> findByNameLike(String name);
}
