package com.qdev.domain.member.service;

import com.qdev.domain.member.entity.Member;
import com.qdev.domain.member.exception.NotSamePasswordAndPasswordConfirmException;
import com.qdev.domain.member.repository.MemberRepository;
import com.qdev.domain.member.request.MemberCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public void createMember(MemberCreateRequest memberCreateRequest) {
        if (!memberCreateRequest.isSamePasswordAndPasswordConfirm()) {
            throw new NotSamePasswordAndPasswordConfirmException();
        }
        memberRepository.save(new Member(memberCreateRequest.getEmail(), memberCreateRequest.getNickname(), memberCreateRequest.getPassword()));
    }
}
