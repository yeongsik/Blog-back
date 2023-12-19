package com.qdev.domain.post.entity;

import com.qdev.domain.category.entity.Category;
import com.qdev.domain.member.entity.Member;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Post {

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
