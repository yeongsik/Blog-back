package com.qdev.domain.member.exception;

import com.qdev.global.error.exception.ApiException;

public class NotFoundMemberException extends ApiException {
    public NotFoundMemberException() {
        super("존재하지 않은 멤버입니다.");
    }
}
