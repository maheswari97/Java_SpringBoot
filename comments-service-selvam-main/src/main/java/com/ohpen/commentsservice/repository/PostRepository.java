package com.ohpen.commentsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohpen.commentsservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
