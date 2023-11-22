package com.qdev.domain.quiz.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuizModifyRequest {
    @NotBlank(message = "퀴즈 제목을 입력해주세요.")
    private String name;
    @NotBlank(message = "퀴즈 설명을 입력해주세요.")
    private String description;

    @Builder
    public QuizModifyRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
