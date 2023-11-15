package com.qdev.domain.quiz.service;

import com.qdev.domain.quiz.entity.Quiz;
import com.qdev.domain.quiz.exception.NotFoundQuizException;
import com.qdev.domain.quiz.repository.QuizRepository;
import com.qdev.domain.quiz.request.QuizCreateRequest;
import com.qdev.domain.quiz.response.QuizReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public void create(QuizCreateRequest quizCreate) {
        quizRepository.save(new Quiz(quizCreate.getName(), quizCreate.getDescription()));
    }

    @Override
    public QuizReadResponse readOne(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(NotFoundQuizException::new);
        return new QuizReadResponse(quiz.getName(), quiz.getDescription());
    }

    @Override
    public List<QuizReadResponse> readAll() {
        return quizRepository.findAll().stream()
                .map(q -> new QuizReadResponse(q.getName(), q.getDescription()))
                .toList();
    }
}
