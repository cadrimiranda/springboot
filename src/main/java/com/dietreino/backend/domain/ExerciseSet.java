package com.dietreino.backend.domain;

import com.dietreino.backend.utils.WeekDays;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exercise_set")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "week_day")
    private WeekDays weekDay;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "set_setup",
            joinColumns = @JoinColumn(name = "exercise_set_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_setup_id")
    )
    private List<ExerciseSetup> exerciseSetupList;
}
