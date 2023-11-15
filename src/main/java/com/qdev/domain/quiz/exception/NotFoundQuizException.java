package com.qdev.domain.quiz.exception;

import com.qdev.global.error.exception.ApiException;

public class NotFoundQuizException extends ApiException {

    public NotFoundQuizException() {
        super("존재하지 않는 퀴즈입니다.");
    }

    public NotFoundQuizException(String message) {
        super(message);
    }

}
