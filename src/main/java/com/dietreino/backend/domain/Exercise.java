package com.dietreino.backend.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "exercise")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Size(min=5, max=56)
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String url;
    @Nullable
    private String image;

    @NotNull
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="muscular_group_id")
    private MuscularGroup muscularGroup;
}
