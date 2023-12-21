package com.qdev.domain.post.controller;

import com.qdev.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostMemberController {

    private final PostService postService;

    /* 회원 사용 기능 */
    /**
     * 게시글 작성
     * 게시글 정보 파라미터
     *
     */
    @PostMapping("/posts")
    public void createPost() {

    }

    /**
     * 게시글 수정
     * @param postId
     * 게시글 정보 파라미터
     */
    @PatchMapping("/posts/{postId}")
    public void modifyPostById(@PathVariable Long postId) {

    }

    /**
     * 게시글 삭제
     * @param postId
     */
    @DeleteMapping("/posts/{postId}")
    public void deletePostById(@PathVariable Long postId) {

    }
}
