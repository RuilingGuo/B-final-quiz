package com.example.demo.exception;

public class PeopleNotFoundException extends RuntimeException {
    public PeopleNotFoundException(String message) {
        super(message);
    }
}
