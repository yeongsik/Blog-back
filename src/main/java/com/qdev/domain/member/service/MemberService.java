package com.qdev.domain.member.service;

import com.qdev.domain.member.request.MemberCreateRequest;

public interface MemberService {
    void createMember(MemberCreateRequest memberCreateRequest);
}
