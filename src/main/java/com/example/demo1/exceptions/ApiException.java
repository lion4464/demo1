package com.example.demo1.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ApiException extends Exception {
    private HttpStatus httpStatus;

    private Object data;

    public ApiException() {
        setHttpStatus(HttpStatus.BAD_REQUEST);
    }

    public ApiException(String message) {
        super(message);
        setHttpStatus(HttpStatus.BAD_REQUEST);
    }

    public ApiException(String message, Object data) {
        super(message);
        setHttpStatus(HttpStatus.BAD_REQUEST);
        setData(data);
    }
}
