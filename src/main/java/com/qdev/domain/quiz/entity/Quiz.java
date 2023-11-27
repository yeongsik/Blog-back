package com.qdev.domain.quiz.entity;

import com.qdev.domain.quiz.request.QuizModifyRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    @Builder
    public Quiz(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void update(QuizModifyRequest quizModifyRequest) {
        name = quizModifyRequest.getName();
        description = quizModifyRequest.getDescription();
    }
}
