package com.example.demo1.exceptions;

public class UserAlreadyExistsException extends ApiException{

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
