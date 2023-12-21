package com.qdev.domain.post.entity;

import com.qdev.domain.member.entity.Member;
import com.qdev.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;

    @ManyToOne
    private Category category;

    @Lob
    private String content;

    @ManyToOne
    private Member writer;

    @OneToMany
    private List<PostFile> postFiles = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "postTag_id")
    )
    private Set<PostTag> tags = new HashSet<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    private Long viewCount;

    private Long likeCount;

    private Boolean isPublic;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @Builder
    public Post(String title, Category category, String content, Member writer, List<PostFile> postFiles, Set<PostTag> tags, List<Comment> comments, Long viewCount, Long likeCount, Boolean isPublic, PostStatus status) {
        this.title = title;
        this.category = category;
        this.content = content;
        this.writer = writer;
        this.postFiles = postFiles;
        this.tags = tags;
        this.comments = comments;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.isPublic = isPublic;
        this.status = status;
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
