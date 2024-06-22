package com.dietreino.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String lastName;
    private String email;
    private String password;
    @Nullable
    private String phone;
    private Boolean active;
    @Column(name = "should_change_password")
    private Boolean shouldChangePassword;

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
    @Nullable
    private Date planStart;

    @Temporal(TemporalType.DATE)
    @Column(name = "plan_expiration")
    @Nullable
    private Date planExpiration;

    @Temporal(TemporalType.DATE)
    @Column(name = "next_appointment")
    @Nullable
    private Date nextAppointment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personal_trainer_id")
    @Nullable
    private User personalTrainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nutritionist_id")
    @Nullable
    private User nutritionist;
}
