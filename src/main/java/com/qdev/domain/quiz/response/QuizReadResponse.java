package com.qdev.domain.quiz.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuizReadResponse {
    private final String name;
    private final String description;
}
