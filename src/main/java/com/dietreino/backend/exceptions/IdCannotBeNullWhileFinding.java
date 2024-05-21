package com.dietreino.backend.exceptions;


public class IdCannotBeNullWhileFinding extends RuntimeException {

    public IdCannotBeNullWhileFinding() {
        super("ID cannot be null while finding ID");
    }

    public IdCannotBeNullWhileFinding(String message) {
        super(message);
    }
}
