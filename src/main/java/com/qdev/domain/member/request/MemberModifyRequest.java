package com.qdev.domain.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberModifyRequest {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotBlank(message = "비밀번호 확인을 입력해주세요.")
    private String passwordConfirm;

    public MemberModifyRequest(String nickname, String password, String passwordConfirm) {
        this.nickname = nickname;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isSamePasswordAndPasswordConfirm() {
        return password.equals(passwordConfirm);
    }
}
