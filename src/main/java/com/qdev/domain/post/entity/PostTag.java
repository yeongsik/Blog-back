package com.qdev.domain.post.entity;

import com.qdev.domain.member.entity.Member;
import com.qdev.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;

@Entity
public class PostTag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Member member;

    // 다대다 관계 설정
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    @Builder
    public PostTag(String name, Member member, Set<Post> posts) {
        this.name = name;
        this.member = member;
        this.posts = posts;
    }
}
