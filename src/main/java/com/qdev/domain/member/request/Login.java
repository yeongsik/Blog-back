package com.qdev.domain.member.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class Login {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private final String email;

    @NotBlank(message = "패스워드를 입력해주세요.")
    private final String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
