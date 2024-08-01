package com.alm.services.implementations;

import com.alm.dtos.comments.CreateCommentDTO;
import com.alm.dtos.comments.GetCommentDTO;
import com.alm.entities.Comment;
import com.alm.entities.Post;
import com.alm.entities.User;
import com.alm.exceptions.CustomRunTimeException;
import com.alm.mappers.CommentMapper;
import com.alm.repositories.CommentRepo;
import com.alm.repositories.PostRepo;
import com.alm.repositories.UserRepo;
import com.alm.services.abstractions.ICommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class CommentService implements ICommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepo commentRepo, PostRepo postRepo, UserRepo userRepo, CommentMapper commentMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.commentMapper = commentMapper;
    }

    @Override
    public GetCommentDTO createComment(CreateCommentDTO createCommentDTO) {
        Post post = validatePost(createCommentDTO.getPostId());
        User user = validateUser(createCommentDTO.getUserId());
        var comment = new Comment();
        comment.setContent(createCommentDTO.getContent());
        comment.setPost(post);
        comment.setUser(user);
        if(createCommentDTO.getParentCommentId() != null) {
            Comment parentComment = commentRepo.findById(createCommentDTO.getParentCommentId())
                    .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The parent comment ID doesn't exist"));
            if(parentComment.getNestingLevel() > 2) {
                throw new CustomRunTimeException("400", HttpStatus.BAD_REQUEST, "The nesting level is too deep for the comment");
            }
            comment.setParentCommentId(parentComment.getParentCommentId());
            comment.setNestingLevel(parentComment.getNestingLevel() + 1);
        }

        var savedComment = commentRepo.save(comment);
        return commentMapper.commentToGetCommentDTO(savedComment);
    }

    @Override
    public List<GetCommentDTO> findAllCommentsByPostId(UUID postId) {
        return null;
    }

    @Override
    public void deleteCommentById(UUID commentId) {

    }

    @Override
    public GetCommentDTO likeComment(UUID commentId) {
        return null;
    }

    @Override
    public GetCommentDTO dislikeComment(UUID commentId) {
        return null;
    }

    public User validateUser(UUID userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The user ID doesn't exist"));
    }

    public Post validatePost(UUID postId) {
        return postRepo.findById(postId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The post ID doesn't exist"));
    }

}
