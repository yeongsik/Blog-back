package com.qdev.domain.quiz.controller;

import com.qdev.domain.quiz.entity.Quiz;
import com.qdev.domain.quiz.repository.QuizRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuizRepository quizRepository;

    @BeforeEach
    void clean() {
        quizRepository.deleteAll();
    }

    @Test
    @DisplayName("퀴즈 작성 시 - 성공")
    void test1() throws Exception {
        mockMvc.perform(post("/quizzes")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\": \"테스트 퀴즈\", \"description\": \"테스트 퀴즈 설명입니다.\" }")
                ).andExpect(status().isOk())
                .andDo(print());
        long cnt = quizRepository.count();
        assertEquals(1L, cnt);
    }

    @Test
    @DisplayName("퀴즈 작성 시 - 실패(퀴즈 제목 미입력)")
    void test2() throws Exception {
        mockMvc.perform(post("/quizzes")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\": \"\", \"description\": \"테스트 퀴즈 설명입니다.\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validationList[*].field").value("name"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("퀴즈 제목을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("퀴즈 작성 시 - 실패(퀴즈 설명 미입력)")
    void test3() throws Exception {
        mockMvc.perform(post("/quizzes")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\": \"테스트 퀴즈\", \"description\": \"\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationList[*].field").value("description"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("퀴즈 설명을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("퀴즈 단건 조회 - 성공")
    void test5() throws Exception {

        Quiz save = quizRepository.save(new Quiz("테스트 제목", "테스트 내용 설명"));


        mockMvc.perform(get("/quizzes/" + save.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("테스트 제목"))
                .andExpect(jsonPath("$.description").value("테스트 내용 설명"))
                .andDo(print());
    }

    @Test
    @DisplayName("퀴즈 단건 조회 - 실패(없는 quizId)")
    void test6() throws Exception {
        mockMvc.perform(get("/quizzes/" + 2L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("존재하지 않는 퀴즈입니다."))
                .andDo(print());
    }
}