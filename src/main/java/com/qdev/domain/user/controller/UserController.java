package com.qdev.domain.user.controller;

import com.qdev.domain.user.request.UserCreateRequest;
import com.qdev.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 생성
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest request) {
        userService.createUser(request);
        return ResponseEntity.ok().build();
    }


    // 회원 단건 조회

    // 회원 다건 조회

    // 회원 수정

    // 회원 삭제
}
