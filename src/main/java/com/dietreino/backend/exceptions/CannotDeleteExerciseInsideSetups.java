package com.dietreino.backend.exceptions;

import java.util.UUID;

public class CannotDeleteExerciseInsideSetups extends RuntimeException {
    public CannotDeleteExerciseInsideSetups() {
        super("Exercicio não pode ser deletado pois o mesmo está presente em uma ou mais rotinas de treino.");
    }

    public CannotDeleteExerciseInsideSetups(UUID id) {
        super("Exercicio com id " + id + " não pode ser deletado pois o mesmo está presente em uma ou mais rotinas de treino.");
    }
}
