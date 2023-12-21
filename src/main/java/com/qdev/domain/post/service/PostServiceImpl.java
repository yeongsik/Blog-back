package com.qdev.domain.post.service;

import com.qdev.domain.post.dto.request.CreatePostRequest;
import com.qdev.domain.post.dto.request.ModifyPostRequest;
import com.qdev.domain.post.dto.request.ReadPostParams;
import com.qdev.domain.post.dto.response.ReadPostResponse;
import com.qdev.domain.post.entity.Category;
import com.qdev.domain.post.entity.PostTag;
import com.qdev.domain.post.repository.CategoryRepository;
import com.qdev.domain.post.repository.PostRepository;
import com.qdev.domain.post.repository.PostTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final PostTagRepository tagRepository;

    @Override
    @Transactional
    public Long createPost(CreatePostRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow();

        if (!request.getTagNames().isEmpty()) {
            return postRepository.save(request
                    .toEntity(category, getPostTags(request.getTagNames()))
            ).getId();
        }

        return postRepository.save(request.toEntity(category)).getId();
    }

    private Set<PostTag> getPostTags(List<String> tagNames) {
        return tagNames.stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseGet(() -> tagRepository.save(PostTag.builder().name(tagName).build())))
                .collect(Collectors.toSet());
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
