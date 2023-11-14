package com.qdev.domain.quiz.controller;

import com.qdev.domain.quiz.request.QuizCreate;
import com.qdev.domain.quiz.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // 퀴즈 작성
    @PostMapping("/quizzes")
    public ResponseEntity<Void> createQuiz(@RequestBody @Valid QuizCreate request) {
        quizService.create(request);
        return ResponseEntity.ok().build();
    }


    // 퀴즈 조회
    @GetMapping("/quizzes/{quizId}")
    public void readQuiz(@PathVariable Long quizId) {

    }

    // 퀴즈 다건 조회

    @GetMapping("/quizzes")
    public void readQuizzes() {

    }

    // 퀴즈 수정

    // 퀴즈 삭제
}
