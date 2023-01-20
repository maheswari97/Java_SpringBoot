package com.ohpen.commentsservice.repository;

import com.ohpen.commentsservice.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByPostid(long Post_id);
}
