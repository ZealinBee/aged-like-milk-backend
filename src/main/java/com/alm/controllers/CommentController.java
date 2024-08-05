package com.alm.controllers;

import com.alm.dtos.comments.CreateCommentDTO;
import com.alm.dtos.comments.GetCommentDTO;
import com.alm.services.abstractions.ICommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<GetCommentDTO> createComment(@RequestBody CreateCommentDTO createCommentDTO, HttpServletRequest req) {
        UUID userId = (UUID) req.getAttribute("userId");
        return ResponseEntity.ok(commentService.createComment(createCommentDTO, userId));
    }

    @PostMapping("/{commentId}/like")
    public ResponseEntity<GetCommentDTO> likeComment(@PathVariable UUID commentId, HttpServletRequest req) {
        UUID userId = (UUID) req.getAttribute("userId");
        return ResponseEntity.ok(commentService.likeComment(commentId, userId));
    }

    @PostMapping("/{commentId}/dislike")
    public ResponseEntity<GetCommentDTO> dislikeComment(@PathVariable UUID commentId, HttpServletRequest req) {
        UUID userId = (UUID) req.getAttribute("userId");
        return ResponseEntity.ok(commentService.dislikeComment(commentId, userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID commentId) {
        commentService.deleteCommentById(commentId);
        return ResponseEntity.noContent().build();
    }
}
