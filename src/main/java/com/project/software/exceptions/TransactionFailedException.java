package com.project.software.exceptions;

public class TransactionFailedException extends RuntimeException {
    public TransactionFailedException(String message) {
        super(message);
    }
}

