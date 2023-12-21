package com.qdev.domain.post.dto.request;

import com.qdev.domain.post.entity.Category;
import com.qdev.domain.post.entity.Post;
import com.qdev.domain.post.entity.PostStatus;
import com.qdev.domain.post.entity.PostTag;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class CreatePostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private Long categoryId;

    private List<String> tagNames;

    @NotBlank
    private Boolean isPublic;

    @NotBlank
    private PostStatus status;

    public Post toEntity(Category category) {
        return Post.builder()
                .title(title)
                .content(content)
                .isPublic(isPublic)
                .status(status)
                .category(category)
                .build();
    }

    public Post toEntity(Category category, Set<PostTag> tags) {
        return Post.builder()
                .title(title)
                .content(content)
                .isPublic(isPublic)
                .status(status)
                .category(category)
                .tags(tags)
                .build();
    }
}
