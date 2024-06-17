package com.dietreino.backend.services;

import com.dietreino.backend.domain.MuscularGroup;
import com.dietreino.backend.dto.MuscularGroup.MuscularGroupRequestDTO;
import com.dietreino.backend.repositories.MuscularGroupRepository;
import com.dietreino.backend.utils.CRUDService;
import com.dietreino.backend.utils.GroupMuscularEnum;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MuscularGroupService extends CRUDService<MuscularGroup, MuscularGroupRequestDTO> {
    private final MuscularGroupRepository muscularGroupRepository;

    @Autowired
    public MuscularGroupService(MuscularGroupRepository muscularGroupRepository) {
        this.muscularGroupRepository = muscularGroupRepository;
    }

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

    public MuscularGroup findByName(GroupMuscularEnum name) {
        return muscularGroupRepository.findReferenceByName(name.toString()).orElseThrow(() ->
                new EntityNotFoundException("Muscular group with name " + name + " not found")
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
    public List<MuscularGroup> findByCriteria(Map<String, String> criteria) {
        return muscularGroupRepository.findByNameLike(criteria.get("name"));
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
