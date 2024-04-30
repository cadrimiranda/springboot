package com.dietreino.backend.services;

import com.dietreino.backend.domain.MuscularGroup;
import com.dietreino.backend.dto.MuscularGroup.MuscularGroupRequestDTO;
import com.dietreino.backend.repositories.MuscularGroupRepository;
import com.dietreino.backend.utils.CRUDService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MuscularGroupService extends CRUDService<MuscularGroup, MuscularGroupRequestDTO> {
    private final MuscularGroupRepository muscularGroupRepository;


    @Override
    public MuscularGroup convertDto(MuscularGroupRequestDTO muscularGroupRequestDTO) {
        MuscularGroup mg = new MuscularGroup();
        mg.setName(muscularGroupRequestDTO.name());
        return mg;
    }

    @Override
    public void validateDto(MuscularGroupRequestDTO muscularGroupRequestDTO) {
        if (muscularGroupRequestDTO.name().length() < 3) {
            throw new IllegalArgumentException("Muscular group name must be at least 3 characters");
        }
    }

    @Override
    public MuscularGroup save(MuscularGroupRequestDTO dto) {
        validateDto(dto);
        MuscularGroup mg = convertDto(dto);
        return muscularGroupRepository.save(mg);
    }

    @Override
    public List<MuscularGroup> findAll() {
        return muscularGroupRepository.findAll();
    }

    @Override
    public MuscularGroup findById(UUID id) {
        return muscularGroupRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Muscular group with id " + id + " not found")
                );
    }

    @Override
    public MuscularGroup update(UUID id, MuscularGroupRequestDTO muscularGroup) {
        MuscularGroup mg = findById(id);
        if(mg == null){
            throw new IllegalArgumentException("Muscular group not found");
        }

        mg.setName(muscularGroup.name());
        return muscularGroupRepository.save(mg);
    }

    @Override
    public List<MuscularGroup> findByCriteria(Map<String, Object> criteria) {
        return muscularGroupRepository.findByNameLike(criteria.get("name").toString());
    }

    @Override
    public void delete(UUID id) {
        MuscularGroup mg = findById(id);
        if(mg == null){
            throw new IllegalArgumentException("Muscular group not found");
        }

        muscularGroupRepository.delete(mg);
    }
}