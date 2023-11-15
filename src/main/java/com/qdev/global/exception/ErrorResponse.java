package com.qdev.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {

    private final String code;
    private final String message;
    private final List<Validation> validationList = new ArrayList<>();

    public void addValidation(FieldError fieldError) {
        validationList.add(new Validation(fieldError.getField(), fieldError.getDefaultMessage()));
    }

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @RequiredArgsConstructor
    @Getter
    public static class Validation {
        private final String field;
        private final String errorMessage;
    }
}
