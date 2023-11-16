package com.qdev.domain.quiz.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class QuizCreateRequest {

    @NotBlank(message = "퀴즈 제목을 입력해주세요.")
    private String name;
    @NotBlank(message = "퀴즈 설명을 입력해주세요.")
    private String description;

    public QuizCreateRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
