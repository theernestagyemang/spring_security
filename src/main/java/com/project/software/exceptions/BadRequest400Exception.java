package com.project.software.exceptions;

public class BadRequest400Exception extends RuntimeException {
    public BadRequest400Exception(String message) {
        super(message);
    }
}
