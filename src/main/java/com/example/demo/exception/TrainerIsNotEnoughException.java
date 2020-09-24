package com.example.demo.exception;

public class TrainerIsNotEnoughException extends RuntimeException {
    public TrainerIsNotEnoughException(String message) {
        super(message);
    }
}
