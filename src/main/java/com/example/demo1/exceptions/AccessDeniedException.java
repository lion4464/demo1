package com.example.demo1.exceptions;

public class AccessDeniedException extends ApiException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
