package com.qdev.domain.member.controller;

import com.qdev.domain.member.request.MemberCreateRequest;
import com.qdev.domain.member.request.MemberModifyRequest;
import com.qdev.domain.member.response.MemberReadResponse;
import com.qdev.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 생성
    @PostMapping("/members")
    public ResponseEntity<Void> createUser(@RequestBody @Valid MemberCreateRequest request) {
        memberService.createMember(request);
        return ResponseEntity.ok().build();
    }

    // 회원 단건 조회
    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberReadResponse> readMember(@PathVariable Long memberId) {
        MemberReadResponse response = memberService.readOne(memberId);
        return ResponseEntity.ok(response);
    }

    // 회원 다건 조회
    @GetMapping("/members")
    public ResponseEntity<List<MemberReadResponse>> readMembers() {
        List<MemberReadResponse> response = memberService.readMembers();
        return ResponseEntity.ok(response);
    }

    // 회원 수정

    @PatchMapping("/members/{memberId}")
    public ResponseEntity<Void> modifyMember(@PathVariable Long memberId,
                                             @RequestBody @Valid MemberModifyRequest request) {
        memberService.modify(memberId, request);

        return ResponseEntity.ok().build();
    }
    // 회원 삭제

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long memberId) {
        memberService.remove(memberId);

        return ResponseEntity.ok().build();
    }
}
