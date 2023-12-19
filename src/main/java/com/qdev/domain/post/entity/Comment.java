package com.qdev.domain.post.entity;

import com.qdev.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Member writer;

    @ManyToOne
    private Post post;

    private Long likeCount;

}
