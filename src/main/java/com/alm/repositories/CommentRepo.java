package com.alm.repositories;

import com.alm.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepo extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByParentCommentId(UUID parentCommentId);
    List<Comment> findAllByPostId(UUID postId);
}
