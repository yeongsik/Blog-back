package com.qdev.domain.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdev.domain.quiz.entity.Quiz;
import com.qdev.domain.quiz.repository.QuizRepository;
import com.qdev.domain.quiz.request.QuizCreateRequest;
import com.qdev.domain.quiz.request.QuizModifyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void clean() {
        quizRepository.deleteAll();
    }

    @Test
    @DisplayName("퀴즈 작성 시 - 성공")
    void test1() throws Exception {

        QuizCreateRequest quizCreateRequest = QuizCreateRequest.builder()
                .name("테스트 퀴즈")
                .description("테스트 퀴즈 설명입니다.")
                .build();
        String content = objectMapper.writeValueAsString(quizCreateRequest);
        mockMvc.perform(post("/quizzes")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isOk())
                .andDo(print());
        assertEquals(1L, quizRepository.count());
    }

    @Test
    @DisplayName("퀴즈 작성 시 - 실패(퀴즈 제목 미입력)")
    void test2() throws Exception {

        QuizCreateRequest quizCreateRequest = QuizCreateRequest.builder()
                .description("테스트 퀴즈 설명입니다.")
                .build();

        String content = objectMapper.writeValueAsString(quizCreateRequest);
        mockMvc.perform(post("/quizzes")
                        .contentType(APPLICATION_JSON)
                        .content(content))
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

        QuizCreateRequest quizCreateRequest = QuizCreateRequest.builder()
                .name("테스트 퀴즈")
                .build();

        String content = objectMapper.writeValueAsString(quizCreateRequest);
        mockMvc.perform(post("/quizzes")
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validationList[*].field").value("description"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("퀴즈 설명을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("퀴즈 단건 조회 - 성공")
    void test5() throws Exception {
        Quiz save = quizRepository.save(Quiz.builder()
                .name("테스트 제목")
                .description("테스트 내용 설명")
                .build());

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

    @Test
    @DisplayName("퀴즈 다건 조회 - 성공")
    void test7() throws Exception {
        quizRepository.saveAll(Arrays.asList(
                Quiz.builder().name("테스트 퀴즈 이름 1").description("테스트 퀴즈 설명 1").build(),
                Quiz.builder().name("테스트 퀴즈 이름 2").description("테스트 퀴즈 설명 2").build(),
                Quiz.builder().name("테스트 퀴즈 이름 3").description("테스트 퀴즈 설명 3").build(),
                Quiz.builder().name("테스트 퀴즈 이름 4").description("테스트 퀴즈 설명 4").build(),
                Quiz.builder().name("테스트 퀴즈 이름 5").description("테스트 퀴즈 설명 5").build(),
                Quiz.builder().name("테스트 퀴즈 이름 6").description("테스트 퀴즈 설명 6").build(),
                Quiz.builder().name("테스트 퀴즈 이름 7").description("테스트 퀴즈 설명 7").build(),
                Quiz.builder().name("테스트 퀴즈 이름 8").description("테스트 퀴즈 설명 8").build(),
                Quiz.builder().name("테스트 퀴즈 이름 9").description("테스트 퀴즈 설명 9").build(),
                Quiz.builder().name("테스트 퀴즈 이름 10").description("테스트 퀴즈 설명 10").build()
        ));

        mockMvc.perform(get("/quizzes")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)))
                .andExpect(jsonPath("$.[*].name", everyItem(containsString("테스트 퀴즈 이름"))))
                .andExpect(jsonPath("$.[*].description", everyItem(containsString("테스트 퀴즈 설명"))))
                .andDo(print());
    }

    @Test
    @DisplayName("퀴즈 수정 - 성공")
    void test8() throws Exception {

        Quiz savedQuiz = quizRepository.save(Quiz.builder()
                .name("테스트 퀴즈 이름")
                .description("테스트 퀴즈 설명")
                .build());

        String content = objectMapper.writeValueAsString(QuizModifyRequest.builder()
                .name("테스트 퀴즈 수정 이름")
                .description("테스트 퀴즈 설명 수정")
                .build());
        mockMvc.perform(patch("/quizzes/" + savedQuiz.getId())
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());

        Quiz modifiedQuiz = quizRepository.findById(savedQuiz.getId()).get();
        assertEquals("테스트 퀴즈 수정 이름", modifiedQuiz.getName());
        assertEquals("테스트 퀴즈 설명 수정", modifiedQuiz.getDescription());
    }

    @Test
    @DisplayName("퀴즈 수정 - 실패(없는 quizId)")
    void test9() throws Exception {

        String content = objectMapper.writeValueAsString(QuizModifyRequest.builder()
                .name("테스트 퀴즈 수정 이름")
                .description("테스트 퀴즈 설명 수정")
                .build());

        mockMvc.perform(patch("/quizzes/" + 1L)
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("존재하지 않는 퀴즈입니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("퀴즈 수정 - 실패(퀴즈 제목 미입력)")
    void test10() throws Exception {

        Quiz savedQuiz = quizRepository.save(Quiz.builder()
                .name("테스트 퀴즈 이름")
                .description("테스트 퀴즈 설명")
                .build());

        String content = objectMapper.writeValueAsString(QuizModifyRequest.builder()
                .description("테스트 퀴즈 설명 수정")
                .build());

        mockMvc.perform(patch("/quizzes/" + savedQuiz.getId())
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validationList[*].field").value("name"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("퀴즈 제목을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("퀴즈 수정 - 실패(퀴즈 설명 미입력)")
    void test11() throws Exception {

        Quiz savedQuiz = quizRepository.save(Quiz.builder()
                .name("테스트 퀴즈 이름")
                .description("테스트 퀴즈 설명")
                .build());

        String content = objectMapper.writeValueAsString(QuizModifyRequest
                .builder()
                .name("테스트 퀴즈 수정 이름")
                .build());

        mockMvc.perform(patch("/quizzes/" + savedQuiz.getId())
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validationList[*].field").value("description"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("퀴즈 설명을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("퀴즈 삭제 - 성공")
    void test12() throws Exception {
        Quiz savedQuiz = quizRepository.save(Quiz.builder()
                .name("테스트 퀴즈 이름")
                .description("테스트 퀴즈 설명")
                .build());

        mockMvc.perform(delete("/quizzes/" + savedQuiz.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        boolean result = quizRepository.findById(savedQuiz.getId()).isEmpty();
        assertTrue(result);
    }

    @Test
    @DisplayName("퀴즈 삭제 - 실패(없는 quizId)")
    void test13() throws Exception {
        mockMvc.perform(delete("/quizzes/" + 1L)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("존재하지 않는 퀴즈입니다."))
                .andDo(print());
    }
}