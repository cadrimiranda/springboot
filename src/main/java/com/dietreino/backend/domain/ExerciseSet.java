package com.dietreino.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exercise_set")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;

    @ManyToMany()
    @JoinTable(
            name = "set_setup",
            joinColumns = @JoinColumn(name = "exercise_set_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_setup_id")
    )
    private List<ExerciseSetup> exerciseSetupList;
}
