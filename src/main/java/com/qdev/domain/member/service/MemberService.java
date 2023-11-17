package com.qdev.domain.member.service;

import com.qdev.domain.member.request.MemberCreateRequest;
import com.qdev.domain.member.request.MemberModifyRequest;
import com.qdev.domain.member.response.MemberReadResponse;

import java.util.List;

public interface MemberService {
    void createMember(MemberCreateRequest memberCreateRequest);

    MemberReadResponse readOne(Long userId);

    List<MemberReadResponse> readMembers();

    void modify(Long memberId, MemberModifyRequest memberModifyRequest);
}
