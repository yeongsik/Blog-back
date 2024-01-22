package com.qdev.domain.post.service;

import com.qdev.domain.post.dto.request.CreatePostRequest;
import com.qdev.domain.post.dto.request.ModifyPostRequest;
import com.qdev.domain.post.dto.request.ReadPostParams;
import com.qdev.domain.post.dto.response.ReadPostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    Long createPost(CreatePostRequest request, MultipartFile[] attachFiles);

    void deletePost();

    void modifyPost(ModifyPostRequest request);

    ReadPostResponse readPost(Long postId);

    Page<ReadPostResponse> readPosts(ReadPostParams params, Pageable pageable);
}
