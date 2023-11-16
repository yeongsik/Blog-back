package com.qdev.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdev.domain.quiz.request.QuizCreateRequest;
import com.qdev.domain.user.repository.UserRepository;
import com.qdev.domain.user.request.UserCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("유저 생성 - 성공")
    void test1() throws Exception {
        String content = objectMapper.writeValueAsString(new UserCreateRequest("테스트 유저 이메일", "테스트 닉네임", "testpassword"));
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isOk())
                .andDo(print());
    }
}