package com.qdev.domain.post.entity;

import com.qdev.domain.member.entity.Member;
import com.qdev.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Comment extends BaseEntity {

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
