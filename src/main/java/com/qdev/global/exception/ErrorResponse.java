package com.qdev.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ErrorResponse {

    private final HttpStatus httpStatus;
    private final String message;
    private final List<Validation> validationList = new ArrayList<>();

    public void addValidation(FieldError fieldError) {
        validationList.add(new Validation(fieldError.getField(), fieldError.getDefaultMessage()));
    }

    @RequiredArgsConstructor
    static class Validation {
        private final String field;
        private final String errorMessage;
    }
}
