package com.jha.test.rest.exception;

public class ValueNotFoundException extends RuntimeException {
    public ValueNotFoundException(String message) {
        super(message);
    }
}
