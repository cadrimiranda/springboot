package com.dietreino.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String email;
    private String password;

    // Workouts created by the coach
    @OneToMany(mappedBy = "createdBy")
    private List<Workout> createdWorkouts;

    // Workouts assigned to the user
    @OneToMany
    @JoinColumn(name = "users_id")
    private List<Workout> assignedWorkouts;

    // Active workout for the user
    @ManyToOne
    @JoinColumn(name = "active_workout_id")
    private Workout activeWorkout;
}
