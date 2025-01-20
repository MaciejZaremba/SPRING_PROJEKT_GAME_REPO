package com.pjait.Games_Data.exceptions;

public class EntityAlreadyExists extends RuntimeException {
    public EntityAlreadyExists() {
        super("Entity already exists.");
    }
}
