package com.qdev.domain.post.service;

import com.qdev.domain.post.dto.request.CreatePostRequest;
import com.qdev.domain.post.dto.request.ModifyPostRequest;
import com.qdev.domain.post.dto.request.ReadPostParams;
import com.qdev.domain.post.dto.response.ReadPostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Override
    public void createPost(CreatePostRequest request) {

    }

    @Override
    public void deletePost() {

    }

    @Override
    public void modifyPost(ModifyPostRequest request) {

    }

    @Override
    public ReadPostResponse readPost(Long postId) {
        return null;
    }

    @Override
    public Page<ReadPostResponse> readPosts(ReadPostParams params, Pageable pageable) {

        return null;
    }
}
