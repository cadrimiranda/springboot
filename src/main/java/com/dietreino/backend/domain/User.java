package com.dietreino.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date dateOfBirth;

    // Active workout for the user
    @ManyToOne
    @JoinColumn(name = "active_workout_id")
    @JsonBackReference
    private Workout activeWorkout;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(
            name = "user_workout", // Name of the join table
            joinColumns = @JoinColumn(name = "user_id"), // Column name in join table for User
            inverseJoinColumns = @JoinColumn(name = "workout_id") // Column name in join table for Group
    )
    private List<Workout> workouts;

    @Temporal(TemporalType.DATE)
    @Column(name = "plan_start")
    private Date planStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "plan_expiration")
    private Date planExpiration;

    @Temporal(TemporalType.DATE)
    @Column(name = "next_appointment")
    private Date nextAppointment;

    @Temporal(TemporalType.DATE)
    @Column(name = "workout_expiration")
    private Date workoutExpiration;
}
