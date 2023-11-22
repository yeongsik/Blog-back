package com.qdev.domain.member.entity;

import com.qdev.domain.member.request.MemberCreateRequest;
import com.qdev.domain.member.request.MemberModifyRequest;
import com.qdev.domain.quiz.request.QuizModifyRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String password;

    private MemberType memberType;

    @Builder
    public Member(String email, String nickname, String password, MemberType memberType) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.memberType = memberType;
    }

    public void update(MemberModifyRequest memberModifyRequest) {
        nickname = memberModifyRequest.getNickname();
        password = memberModifyRequest.getPassword();
    }

    public static Member of(MemberCreateRequest request) {
        return Member.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .memberType(MemberType.NORMAL)
                .build();
    }

}
