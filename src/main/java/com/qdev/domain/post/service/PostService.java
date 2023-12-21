package com.qdev.domain.post.service;

import com.qdev.domain.post.dto.request.CreatePostRequest;
import com.qdev.domain.post.dto.request.ModifyPostRequest;
import com.qdev.domain.post.dto.request.ReadPostParams;
import com.qdev.domain.post.dto.response.ReadPostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    void createPost(CreatePostRequest request);

    void deletePost();

    void modifyPost(ModifyPostRequest request);

    ReadPostResponse readPost(Long postId);

    Page<ReadPostResponse> readPosts(ReadPostParams params, Pageable pageable);
}
