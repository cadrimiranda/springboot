package com.dietreino.backend.infra.exception;

import com.dietreino.backend.exceptions.IdCannotBeNullWhileFinding;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ErrorMessage> illegalArgument(IllegalArgumentException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<ErrorMessage> runtimeException(RuntimeException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(IdCannotBeNullWhileFinding.class)
    private ResponseEntity<ErrorMessage> idCannotBeNullWhileFinding(IdCannotBeNullWhileFinding exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
