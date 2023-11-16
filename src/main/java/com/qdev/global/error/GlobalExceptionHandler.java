package com.qdev.global.error;

import com.qdev.global.error.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validHandler(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse("400.1", "잘못된 요청입니다.");
        for (FieldError fieldError : e.getFieldErrors()) {
            errorResponse.addValidation(fieldError);
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> apiExceptionHandler(ApiException e) {
        ErrorResponse errorResponse = new ErrorResponse("400.1", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
