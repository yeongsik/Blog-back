package com.qdev.domain.member.service;

import com.qdev.domain.member.entity.Member;
import com.qdev.domain.member.entity.MemberType;
import com.qdev.domain.member.exception.NotFoundMemberException;
import com.qdev.domain.member.exception.NotSamePasswordAndPasswordConfirmException;
import com.qdev.domain.member.repository.MemberRepository;
import com.qdev.domain.member.request.MemberCreateRequest;
import com.qdev.domain.member.request.MemberModifyRequest;
import com.qdev.domain.member.response.MemberReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void createMember(MemberCreateRequest memberCreateRequest) {
        if (!memberCreateRequest.isSamePasswordAndPasswordConfirm()) {
            throw new NotSamePasswordAndPasswordConfirmException();
        }
        memberRepository.save(Member.of(memberCreateRequest));
    }

    @Override
    public MemberReadResponse readOne(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);

        return MemberReadResponse.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .memberType(member.getMemberType())
                .build();
    }

    @Override
    public List<MemberReadResponse> readMembers() {
        return memberRepository.findAll().stream()
                .map(m -> new MemberReadResponse(m.getEmail(), m.getNickname(), m.getMemberType()))
                .toList();
    }

    @Transactional
    @Override
    public void modify(Long memberId, MemberModifyRequest memberModifyRequest) {
        if (!memberModifyRequest.isSamePasswordAndPasswordConfirm()) {
            throw new NotSamePasswordAndPasswordConfirmException();
        }

        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
        member.update(memberModifyRequest);
    }

    @Override
    public void remove(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
        memberRepository.delete(member);
    }
}
