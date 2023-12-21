package com.qdev.domain.post.entity;

import com.qdev.domain.category.entity.Category;
import com.qdev.domain.member.entity.Member;
import com.qdev.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String subject;

    @ManyToOne
    private Category category;

    @Lob
    private String content;

    @ManyToOne
    private Member writer;

    @OneToMany
    private List<PostFile> postFiles;

    @OneToMany
    private List<PostTag> postTags;

    @OneToMany
    private List<Comment> comments;

    private Long viewCount;

    private Long likeCount;

    private Boolean isPublic;

    @Builder
    public Post(Long id, String subject, Category category, String content, Member writer, List<PostFile> postFiles, List<PostTag> postTags, List<Comment> comments, Long viewCount, Long likeCount, Boolean isPublic) {
        Id = id;
        this.subject = subject;
        this.category = category;
        this.content = content;
        this.writer = writer;
        this.postFiles = postFiles;
        this.postTags = postTags;
        this.comments = comments;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.isPublic = isPublic;
    }

    /*
    제목

    카테고리

    글 내용

    작성자

    첨부파일들

    태그들

    조회수

    좋아요 수

    삭제 유무
    공개 유무
    수정일
    삭제일
    등록일
    */
}
