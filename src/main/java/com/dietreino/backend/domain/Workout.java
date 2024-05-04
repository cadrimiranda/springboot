package com.dietreino.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "workout")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Workout  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;

    // User who created the workout
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @OneToMany
    @JoinColumn(name = "workout_id")
    private List<ExerciseSet> exerciseSets;
}
