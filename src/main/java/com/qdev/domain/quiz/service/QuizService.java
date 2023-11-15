package com.qdev.domain.quiz.service;

import com.qdev.domain.quiz.request.QuizCreateRequest;
import com.qdev.domain.quiz.response.QuizReadResponse;

import java.util.List;

public interface QuizService {

    void create(QuizCreateRequest quizCreate);

    QuizReadResponse readOne(Long quizId);

    List<QuizReadResponse> readAll();
}
