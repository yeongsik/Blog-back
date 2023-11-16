package com.qdev.domain.member.controller;

import com.qdev.domain.member.request.MemberCreateRequest;
import com.qdev.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 생성
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody @Valid MemberCreateRequest request) {
        memberService.createMember(request);
        return ResponseEntity.ok().build();
    }


    // 회원 단건 조회

    // 회원 다건 조회

    // 회원 수정

    // 회원 삭제
}
