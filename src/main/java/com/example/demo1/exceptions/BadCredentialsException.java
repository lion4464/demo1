package com.example.demo1.exceptions;

public class BadCredentialsException extends ApiException{
    public BadCredentialsException(String message) {
        super(message);
    }
}
