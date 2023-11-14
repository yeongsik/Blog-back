package com.qdev.domain.quiz.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class QuizCreate {

    @NotBlank(message = "퀴즈 제목을 입력해주세요.")
    private String name;

    @NotBlank(message = "퀴즈 설명을 입력해주세요.")
    private String description;

}
