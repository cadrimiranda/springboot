package com.dietreino.backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "exercise")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotBlank
    @Size(min=5, max=56)
    private String name;
    @Nullable
    private String description;

    @NotNull
    @NotBlank
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="muscular_group_id")
    private MuscularGroup muscularGroup;
}
