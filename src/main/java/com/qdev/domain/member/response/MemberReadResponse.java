package com.qdev.domain.member.response;

import com.qdev.domain.member.entity.MemberType;
import lombok.Getter;

@Getter
public class MemberReadResponse {

    private final String email;
    private final String nickname;
    private final MemberType memberType;

    public MemberReadResponse(String email, String nickname, MemberType memberType) {
        this.email = email;
        this.nickname = nickname;
        this.memberType = memberType;
    }
}
