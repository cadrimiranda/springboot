package com.dietreino.backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "anthropometric_report")
@Data
@Builder
@AllArgsConstructor
public class AnthropometricReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @NotNull(message = "The date of the report is required.")
    @PastOrPresent(message = "The date of the report must be today or in the past.")
    private LocalDate reportDate;

    @NotNull
    @DecimalMin(value = "0.5", message = "Height must be a positive number.")
    @DecimalMax(value = "2.5", message = "Height is not in a valid range.")
    private double height;

    @NotNull
    @DecimalMin(value = "10.0", message = "Weight must be a positive number.")
    private double weight;

    @NotNull
    @DecimalMin(value = "10.0", message = "Ideal weight must be a positive number.")
    private double idealWeight;

    @NotNull
    private double bmi;

    @NotNull
    private double bodyFatMass;

    @NotNull
    @DecimalMin(value = "0", message = "Percentage of body fat cannot be negative.")
    @DecimalMax(value = "100", message = "Percentage of body fat cannot exceed 100.")
    private double percentageBodyFat;

    @NotNull
    private double leanMass;

    @NotNull
    @DecimalMin(value = "0", message = "Percentage of lean mass cannot be negative.")
    @DecimalMax(value = "100", message = "Percentage of lean mass cannot exceed 100.")
    private double percentageLeanMass;

    @NotNull
    private double bodyDensity;

    @NotNull
    private double sumOfSkinfolds;

    @NotNull
    private double armMuscleArea;

    @NotNull
    private double armFatArea;

    // Circumferences
    @NotNull
    private double shoulderCircumference;

    @NotNull
    private double chestCircumference;

    @NotNull
    private double waistCircumference;

    @NotNull
    private double abdomenCircumference;

    @NotNull
    private double rightCalfCircumference;

    @NotNull
    private double leftCalfCircumference;

    @NotNull
    private double rightThighCircumference;

    @NotNull
    private double leftThighCircumference;

    @NotNull
    private double relaxedRightArmCircumference;

    @NotNull
    private double relaxedLeftArmCircumference;

    @NotNull
    private double contractedRightArmCircumference;

    @NotNull
    private double contractedLeftArmCircumference;

    @NotNull
    private double rightForearmCircumference;

    @NotNull
    private double leftForearmCircumference;

    // Skinfolds
    @NotNull
    private double tricepsSkinfold;

    @NotNull
    private double midAxillarySkinfold;

    @NotNull
    private double chestSkinfold;

    @NotNull
    private double abdominalSkinfold;

    @NotNull
    private double suprailiacSkinfold;

    @NotNull
    private double subscapularSkinfold;

    @NotNull
    private double thighSkinfold;

    protected AnthropometricReport() {

    }
}
