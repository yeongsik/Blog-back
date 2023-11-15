package com.qdev.domain.quiz.service;

import com.qdev.domain.quiz.request.QuizCreateRequest;
import com.qdev.domain.quiz.response.QuizReadResponse;

public interface QuizService {

    void create(QuizCreateRequest quizCreate);

    QuizReadResponse readOne(Long quizId);
}
