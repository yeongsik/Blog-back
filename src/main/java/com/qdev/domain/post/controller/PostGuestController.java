package com.qdev.domain.post.controller;

import com.qdev.domain.post.dto.request.ReadPostParams;
import com.qdev.domain.post.dto.response.ReadPostResponse;
import com.qdev.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostGuestController {

    private final PostService postService;

    /**
     * 게시글 리스트 조회
     * 사용자 입장에서의 게시글 리스트 조회
     * 검색 조건, 정렬 조건 파라미터로 들어감
     * 페이징 처리
     */
    @GetMapping("/posts")
    public ResponseEntity<Page<ReadPostResponse>> getPosts(@ModelAttribute ReadPostParams params, Pageable pageable) {
        // 로그인 여부 체크
        // 회원이면 내가 좋아요한 글 표시하기
        return ResponseEntity.ok(postService.readPosts(params, pageable));
    }

    /**
     * 게시글 조회
     * 댓글 리스트 조회
     * 게시글 정보 (이미지, 첨부파일, 게시글 내용, 좋아요 수, 조회수, 본인 글인지 아닌지 여부)
     *
     * @param postId
     */
    @GetMapping("/posts/{postId}")
    public ResponseEntity<ReadPostResponse> getPostById(@PathVariable Long postId) {
        // 로그인 여부 체크
        return ResponseEntity.ok(postService.readPost(postId));
    }
}
