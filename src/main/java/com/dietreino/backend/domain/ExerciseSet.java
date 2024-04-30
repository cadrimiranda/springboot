package com.dietreino.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="exercise_set")
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

    @ManyToMany
    @JoinTable(
            name = "setups",
            joinColumns = @JoinColumn(name = "exercise_set_id"),
            inverseJoinColumns = @JoinColumn(name = "exercse_setup_id")
    )
    @JsonManagedReference
    private List<ExerciseSetup> exerciseSetupList;

    @ManyToMany(mappedBy = "exerciseSets")
    @JsonBackReference
    private List<Workout> workoutList;
}
