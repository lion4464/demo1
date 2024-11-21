package com.example.demo1.exceptions;

public class DataNotFoundException extends ApiException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
