package com.qdev.domain.quiz.controller;

import com.qdev.domain.quiz.request.QuizCreateRequest;
import com.qdev.domain.quiz.response.QuizReadResponse;
import com.qdev.domain.quiz.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // 퀴즈 작성
    @PostMapping("/quizzes")
    public ResponseEntity<Void> createQuiz(@RequestBody @Valid QuizCreateRequest request) {
        quizService.create(request);
        return ResponseEntity.ok().build();
    }


    // 퀴즈 조회
    @GetMapping("/quizzes/{quizId}")
    public ResponseEntity<QuizReadResponse> readQuiz(@PathVariable Long quizId) {
        QuizReadResponse quizRead = quizService.readOne(quizId);
        return ResponseEntity.ok(quizRead);
    }

    // 퀴즈 다건 조회
    @GetMapping("/quizzes")
    public ResponseEntity<List<QuizReadResponse>> readQuizzes() {
        return ResponseEntity.ok(quizService.readAll());
    }

    // 퀴즈 수정
    @PatchMapping("/quizzes/{quizId}")
    public ResponseEntity<Void> modifyQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok().build();
    }

    // 퀴즈 삭제
    @DeleteMapping("/quizzes/{quizId}")
    public ResponseEntity<Void> removeQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok().build();
    }
}
