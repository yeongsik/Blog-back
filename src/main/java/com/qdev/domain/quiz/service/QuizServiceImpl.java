package com.qdev.domain.quiz.service;

import com.qdev.domain.quiz.entity.Quiz;
import com.qdev.domain.quiz.repository.QuizRepository;
import com.qdev.domain.quiz.request.QuizCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public void create(QuizCreate quizCreate) {
        quizRepository.save(new Quiz(quizCreate.getName(), quizCreate.getDescription()));
    }
}
