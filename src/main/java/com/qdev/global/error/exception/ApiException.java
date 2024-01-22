package com.qdev.global.error.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
