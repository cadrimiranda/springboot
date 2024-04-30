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
@Table(name="exercise_setup")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseSetup {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String series;
    private String repetitions;
    private String rest;
    private String observation;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @JsonBackReference
    @ManyToMany(mappedBy = "exerciseSetupList", fetch = FetchType.LAZY)
    private List<ExerciseSet> exerciseSets;
}
