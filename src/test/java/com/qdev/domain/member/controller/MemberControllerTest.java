package com.qdev.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdev.domain.member.entity.Member;
import com.qdev.domain.member.entity.MemberType;
import com.qdev.domain.member.repository.MemberRepository;
import com.qdev.domain.member.request.MemberCreateRequest;
import com.qdev.domain.member.request.MemberModifyRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원 생성 - 성공")
    void createMemberSuccess() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "테스트 닉네임", "testpassword" ,
                        "testpassword"));
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, memberRepository.count());
    }

    @Test
    @DisplayName("회원 생성 - 실패 (이메일 미입력)")
    void createMemberFailBecauseInvalidInputOfEmail() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("",
                        "테스트 닉네임",
                        "testpassword",
                        "testpassword"));
        mockMvc.perform(post("/members")
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
    @DisplayName("회원 생성 - 실패 (이메일 패턴이 아닐 때)")
    void createMemberFailBecauseInvalidInputOfEmailPattern() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("testgmail.com",
                        "테스트 닉네임",
                        "testpassword",
                        "testpassword"));
        mockMvc.perform(post("/members")
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
    @DisplayName("회원 생성 - 실패 (닉네임 미입력)")
    void createMemberFailBecauseInvalidInputOfNickname() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "",
                        "testpassword",
                        "testpassword"));
        mockMvc.perform(post("/members")
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
    @DisplayName("회원 생성 - 실패 (비밀번호 미입력)")
    void createMemberFailBecauseInvalidInputOfPassword() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "테스트 닉네임",
                        "   ",
                        "testpassword"));
        mockMvc.perform(post("/members")
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
    @DisplayName("회원 생성 - 실패 (비밀번호 확인 미입력)")
    void createMemberFailBecauseInvalidInputOfPasswordConfirm() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "테스트 닉네임",
                        "testpassword",
                        "   "));
        mockMvc.perform(post("/members")
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
    @DisplayName("회원 생성 - 실패 (비밀번호와 비밀번호 확인 불일치)")
    void createMemberFailBecauseNotSamePasswordAndPasswordConfirm() throws Exception {
        String content = objectMapper.writeValueAsString(
                new MemberCreateRequest("test@gmail.com",
                        "테스트 닉네임",
                        "testpassword1234",
                        "testpassword"));
        mockMvc.perform(post("/members")
                        .contentType(APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("비밀번호와 비밀번호 확인이 일치하지 않습니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("회원 단건 조회 - 성공")
    void readOneMemberSuccess() throws Exception {
        // given
        Member saveMember = memberRepository.save(new Member("test1234@gmail.com", "테스트 닉네임", "password@1234", MemberType.NORMAL));

        mockMvc.perform(get("/members/" + saveMember.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test1234@gmail.com"))
                .andExpect(jsonPath("$.nickname").value("테스트 닉네임"))
                .andExpect(jsonPath("$.memberType").value("NORMAL"))
                .andDo(print());

    }

    @Test
    @DisplayName("회원 단건 조회 - 실패(존재하지 않은 회원)")
    void readOneMemberFailBecauseNotFoundMember() throws Exception {

        // expected
        mockMvc.perform(get("/members/" + 1L))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("존재하지 않은 회원입니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("회원 다건 조회 - 성공")
    void readAllMemberSuccess() throws Exception {
        memberRepository.saveAll(Arrays.asList(
                Member.builder().email("test1@gmail.com").nickname("테스트 닉네임1").password("test@1234").memberType(MemberType.NORMAL).build(),
                Member.builder().email("test2@gmail.com").nickname("테스트 닉네임2").password("test@1234").memberType(MemberType.NORMAL).build(),
                Member.builder().email("test3@gmail.com").nickname("테스트 닉네임3").password("test@1234").memberType(MemberType.NORMAL).build(),
                Member.builder().email("test4@gmail.com").nickname("테스트 닉네임4").password("test@1234").memberType(MemberType.NORMAL).build(),
                Member.builder().email("test5@gmail.com").nickname("테스트 닉네임5").password("test@1234").memberType(MemberType.NORMAL).build()
        ));

        // expected
        mockMvc.perform(get("/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$" , Matchers.hasSize(5)))
//                .andExpect(jsonPath("$.message").value("존재하지 않은 회원입니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("회원 수정 - 성공")
    void modifyMemberSuccess() throws Exception {
        // given
        Member saveMember = memberRepository.save(Member.builder().email("test1234@gmail.com")
                .nickname("테스트 닉네임")
                .password("password@1234")
                .memberType(MemberType.NORMAL)
                .build());

        String json = objectMapper.writeValueAsString(MemberModifyRequest.builder()
                .nickname("테스트 닉네임 수정")
                .password("test1234567@")
                .passwordConfirm("test1234567@")
                .build());


        // when
        mockMvc.perform(patch("/members/" + saveMember.getId())
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        // then
        Member modifiedMember = memberRepository.findById(saveMember.getId()).get();
        assertEquals("테스트 닉네임 수정" , modifiedMember.getNickname());
        assertEquals("test1234567@" , modifiedMember.getPassword());
    }

    @Test
    @DisplayName("회원 삭제 - 성공")
    void removeMemberSuccess() throws Exception {
        // given
        Member saveMember = memberRepository.save(Member.builder()
                .email("test1234@gmail.com")
                .nickname("테스트 닉네임")
                .password("password@1234")
                .memberType(MemberType.NORMAL)
                .build());

        mockMvc.perform(delete("/members/" + saveMember.getId()))
                .andExpect(status().isOk())
                .andDo(print());

        assertTrue(memberRepository.findById(saveMember.getId()).isEmpty());
    }

    @Test
    @DisplayName("회원 삭제 - 실패(존재 하지 않는 회원)")
    void removeMemberFailBecauseNotFoundMember() throws Exception {
        //expected
        mockMvc.perform(delete("/members/" + 1L))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400.1"))
                .andExpect(jsonPath("$.message").value("존재하지 않은 회원입니다."))
                .andDo(print());
    }

}