package com.qdev.domain.quiz.service;

import com.qdev.domain.quiz.request.QuizCreate;
import com.qdev.domain.quiz.response.QuizRead;

public interface QuizService {

    void create(QuizCreate quizCreate);

    QuizRead readOne(Long quizId);
}
