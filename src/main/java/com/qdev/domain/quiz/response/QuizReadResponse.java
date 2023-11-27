package com.qdev.domain.quiz.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class QuizReadResponse {
    private String name;
    private String description;

    @Builder
    public QuizReadResponse(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
