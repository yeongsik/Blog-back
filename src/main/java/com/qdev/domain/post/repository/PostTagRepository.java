package com.qdev.domain.post.repository;

import com.qdev.domain.post.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

}
