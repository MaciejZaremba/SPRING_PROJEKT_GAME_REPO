package com.pjait.Games_Data.exceptions;

public class EntityNotFound extends RuntimeException {
    public EntityNotFound() {
        super("Entity not found.");
    }
}
