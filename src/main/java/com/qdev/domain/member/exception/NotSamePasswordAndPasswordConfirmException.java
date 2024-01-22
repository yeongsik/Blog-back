package com.qdev.domain.member.exception;

import com.qdev.global.error.exception.ApiException;

public class NotSamePasswordAndPasswordConfirmException extends ApiException {
    public NotSamePasswordAndPasswordConfirmException() {
        super("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    }
}
