package com.qdev.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdev.domain.member.repository.MemberRepository;
import com.qdev.domain.member.request.MemberCreateRequest;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("유저 생성 - 성공")
    void test1() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "테스트 닉네임", "testpassword" ,
                        "testpassword"));
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, memberRepository.count());
    }

    @Test
    @DisplayName("유저 생성 - 실패 (이메일 미입력)")
    void test2() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("",
                        "테스트 닉네임",
                        "testpassword",
                        "testpassword"));
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validationList[*].field").value("email"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("이메일을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("유저 생성 - 실패 (이메일 패턴이 아닐 때)")
    void test3() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("testgmail.com",
                        "테스트 닉네임",
                        "testpassword",
                        "testpassword"));
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validationList[*].field").value("email"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("이메일 형식에 맞지 않습니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("유저 생성 - 실패 (닉네임 미입력)")
    void test4() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "",
                        "testpassword",
                        "testpassword"));
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validationList[*].field").value("nickname"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("닉네임을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("유저 생성 - 실패 (비밀번호 미입력)")
    void test5() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "테스트 닉네임",
                        "   ",
                        "testpassword"));
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validationList[*].field").value("password"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("비밀번호를 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("유저 생성 - 실패 (비밀번호 확인 미입력)")
    void test6() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "테스트 닉네임",
                        "testpassword",
                        "   "));
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validationList[*].field").value("passwordConfirm"))
                .andExpect(jsonPath("$.validationList[*].errorMessage").value("비밀번호 확인을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("유저 생성 - 실패 (비밀번호와 비밀번호 확인 불일치)")
    void test7() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "테스트 닉네임",
                        "testpassword1234",
                        "testpassword"));
        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("비밀번호와 비밀번호 확인이 일치하지 않습니다."))
                .andDo(print());
    }
}