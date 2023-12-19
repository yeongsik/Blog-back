package com.qdev.domain.category.entity;

import com.qdev.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isPublic;

    @ManyToOne
    private Member writer;



}
