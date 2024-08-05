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
    public GetCommentDTO createComment(CreateCommentDTO createCommentDTO, UUID userId) {
        Post post = validatePost(createCommentDTO.getPostId());
        System.out.println("HELLO" + userId);
        User user = validateUser(userId);
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
            comment.setParentCommentId(parentComment.getId());
            comment.setNestingLevel(parentComment.getNestingLevel() + 1);
        }

        var savedComment = commentRepo.save(comment);
        return commentMapper.commentToGetCommentDTO(savedComment);
    }


    @Override
    public void deleteCommentById(UUID commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The comment ID doesn't exist"));
        // TODO: figure out a recursive way to delete all the children comments
        commentRepo.delete(comment);
    }

    @Override
    public List<GetCommentDTO> getCommentsByPostId(UUID postId) {
        Post post = validatePost(postId);
        List<Comment> comments = commentRepo.findAllByPostId(postId);
        return comments.stream()
                .map(commentMapper::commentToGetCommentDTO)
                .toList();
    }

    @Override
    public GetCommentDTO likeComment(UUID commentId, UUID userId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The comment ID doesn't exist"));
        User user = validateUser(userId);
        // if the user has already liked the comment, remove the like
        if(user.getLikedComments().contains(comment.getId())) {
            user.getLikedComments().remove(comment.getId());
            comment.setLikes(comment.getLikes() - 1);
        } else {
            user.getLikedComments().add(comment.getId());
            comment.setLikes(comment.getLikes() + 1);
        }
        // if the user has disliked the comment, remove the dislike
        if(user.getDislikedComments().contains(comment.getId())) {
            user.getDislikedComments().remove(comment.getId());
            comment.setDislikes(comment.getDislikes() - 1);
        }

        commentRepo.save(comment);
        userRepo.save(user);
        return commentMapper.commentToGetCommentDTO(comment);
    }


    @Override
    public GetCommentDTO dislikeComment(UUID commentId, UUID userId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new CustomRunTimeException("404", HttpStatus.NOT_FOUND, "The comment ID doesn't exist"));
        User user = validateUser(userId);
        // if the user has already disliked the comment, remove the dislike
        if(user.getDislikedComments().contains(comment.getId())) {
            user.getDislikedComments().remove(comment.getId());
            comment.setDislikes(comment.getDislikes() - 1);
        }else {
            user.getDislikedComments().add(comment.getId());
            comment.setDislikes(comment.getDislikes() + 1);
        }
        // if the user has liked the comment, remove the like
        if(user.getLikedComments().contains(comment.getId())) {
            user.getLikedComments().remove(comment.getId());
            comment.setLikes(comment.getLikes() - 1);
        }
        commentRepo.save(comment);
        userRepo.save(user);
        return commentMapper.commentToGetCommentDTO(comment);
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
