package com.dietreino.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="muscular_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MuscularGroup  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "muscularGroup", fetch = FetchType.LAZY)
    private List<Exercise> exercises;

    public MuscularGroup(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
