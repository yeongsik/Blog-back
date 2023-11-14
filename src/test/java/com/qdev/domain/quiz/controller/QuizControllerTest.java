package com.qdev.domain.quiz.controller;

import com.qdev.domain.quiz.repository.QuizRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuizRepository quizRepository;

    @Test
    @DisplayName("퀴즈 작성 시 - 성공")
    void test1() throws Exception {
        mockMvc.perform(post("/quizzes")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\": \"테스트 퀴즈\", \"description\": \"테스크 퀴즈 설명입니다ㅏ.\" }")
                ).andExpect(status().isOk())
                .andDo(print());

        long cnt = quizRepository.count();

        assertEquals(1L, cnt);
    }
}