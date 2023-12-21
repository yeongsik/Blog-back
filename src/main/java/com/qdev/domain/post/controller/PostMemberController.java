package com.qdev.domain.post.controller;

import com.qdev.domain.post.dto.request.CreatePostRequest;
import com.qdev.domain.post.service.PostFileService;
import com.qdev.domain.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostMemberController {

    private final PostService postService;
    private final PostFileService postFileService;

    /* 회원 사용 기능 */

    /**
     * 게시글 작성
     * 게시글 정보 파라미터
     */
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestParam(required = false) MultipartFile[] attachFiles, @RequestBody @Valid CreatePostRequest request) {
        postFileService.saveFiles(attachFiles);
        Long createdPostId = postService.createPost(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPostId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * 게시글 수정
     *
     * @param postId 게시글 정보 파라미터
     */
    @PatchMapping("/{postId}")
    public ResponseEntity<Void> modifyPostById(@PathVariable Long postId) {
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 삭제
     *
     * @param postId
     */
    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable Long postId) {

    }
}
